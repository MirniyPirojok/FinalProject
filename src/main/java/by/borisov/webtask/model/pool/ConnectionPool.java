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

    static Properties properties = DatabasePropertiesLoader.loadProperties();
    public static final int DEFAULT_POOL_SIZE = Integer.parseInt(properties.getProperty("poolsize"));

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> unavailableConnections;

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.fatal("Database driver was not registered.", e);
            throw new RuntimeException(e);
        }
        initConnections();
    }

    private void initConnections() {
        availableConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        ProxyConnection connection = null;
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                connection = ConnectionCreator.createConnection(properties);
            } catch (SQLException e) {
                logger.error("Connection was not created.");
            }

            if (connection != null) {
                logger.info(String.format("Connection #%d was created.", i));
                availableConnections.offer(connection);
            }
        }

        if (availableConnections.isEmpty()) {
            logger.fatal("Connection pool was not initialized.");
            throw new ExceptionInInitializerError();
        } else {
            logger.info("Connection pool was initialized.");
        }

        unavailableConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
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
            connection = availableConnections.take();
            unavailableConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Connection was not taken. Connection leak.");
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionException {
        if (connection instanceof ProxyConnection && unavailableConnections.remove(connection)) {
            try {
                availableConnections.put((ProxyConnection) connection);
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
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().reallyClose();
            } catch (InterruptedException e) {
                logger.error("Closing connection error", e);
                throw new ConnectionException(e);
            }
        }

        if (availableConnections.isEmpty()) {
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
