package by.borisov.webtask.model.pool;

import by.borisov.webtask.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is used to create connections, give them away and get back.
 */
public class ConnectionPool {
    static Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static AtomicBoolean isPoolCreated = new AtomicBoolean(false);

    private static Properties properties = DBPropertiesLoader.loadProperties();
    public static final String DRIVER = "db.driver";
    public static final String DEFAULT_POOL_SIZE = "poolsize";
    private static final String URL = "db.url";

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> busyConnections;

    private ConnectionPool() {
        try {
            Class.forName(properties.getProperty(DRIVER));
        } catch (ClassNotFoundException e) {
            logger.fatal("Database driver was not registered.", e);
            throw new RuntimeException(e);
        }
        initPool();
    }

    private void initPool() {
        int poolSize = Integer.parseInt(properties.getProperty(DEFAULT_POOL_SIZE));
        busyConnections = new LinkedBlockingDeque<>(poolSize);
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        ConnectionCreator connectionCreator = ConnectionCreator.getInstance();
        String url = properties.getProperty(URL);
        for (int i = 0; i < poolSize; i++) {
            freeConnections.offer(connectionCreator.createConnection(url, properties));
            logger.info(String.format("Connection #%d was created.", i));
        }

        if (freeConnections.isEmpty()) {
            logger.fatal("Connection pool was not initialized.");
            throw new ExceptionInInitializerError();
        } else {
            logger.info("Connection pool was initialized.");
        }
    }

    public static ConnectionPool getInstance() {
        if (!isPoolCreated.get()) {
            try {
                locker.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isPoolCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Connection was not taken. Connection leak.");
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionException {
        if (connection instanceof ProxyConnection && busyConnections.remove(connection)) {
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Connection was not released. Connection leak.");
                Thread.currentThread().interrupt();
            }
        } else {
            logger.error("Connection was not released.");
            throw new ConnectionException("Connection wasn't released.");
        }
    }

    public void destroyPool() throws ConnectionException {
        int poolSize = Integer.parseInt(properties.getProperty(DEFAULT_POOL_SIZE));
        for (int i = 0; i < poolSize; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                logger.error("Closing connection error", e);
                throw new ConnectionException(e);
            }
        }

        if (freeConnections.isEmpty()) {
            logger.info("Connections pool was destroyed.");
        } else {
            logger.error("Connection pool was not destroyed.");
        }

        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Database driver was not deregistered.", e);
            }
            logger.info("Database driver was deregistered.");
        });
    }
}
