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
 * Class is used to create connections, give them out and return.
 */
public class ConnectionPool {
    static Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static AtomicBoolean isPoolCreated = new AtomicBoolean(false);

    private static final Properties properties = DBPropertiesLoader.loadProperties();
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    public static final int DEFAULT_POOL_SIZE = 32;

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> busyConnections;

    static {
        try {
            Class.forName(properties.getProperty(DRIVER));
        } catch (ClassNotFoundException e) {
            logger.fatal("Database driver was not registered.", e);
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool() {
        busyConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        ConnectionCreator connectionCreator = ConnectionCreator.getInstance();
        String url = properties.getProperty(URL);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.put(connectionCreator.createConnection(url));
            } catch (InterruptedException e) {
                logger.error("Connection was not added to freeConnections.");
                Thread.currentThread().interrupt();
            }
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

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            if (connection instanceof ProxyConnection && busyConnections.remove(connection)) {
                freeConnections.put((ProxyConnection) connection);
            }
        } catch (InterruptedException e) {
            logger.error("Connection was not released. Connection leak.");
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            logger.error("Can't set autocommit to true.", e);
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                logger.error("Closing connection error", e);
                Thread.currentThread().interrupt();
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
