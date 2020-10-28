package by.borisov.webtask.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {
    static Logger logger = LogManager.getLogger();

    private static ConnectionCreator instance;

    private ConnectionCreator() {
    }

    static ConnectionCreator getInstance() {
        if (instance == null) {
            instance = new ConnectionCreator();
        }
        return instance;
    }

    ProxyConnection createConnection(String url) {
        ProxyConnection proxyConnection = null;
        try {
            Connection connection = DriverManager.getConnection(url, DBPropertiesLoader.loadProperties());
            proxyConnection = new ProxyConnection(connection);
        } catch (SQLException e) {
            logger.error("Connection was not created.", e);
        }
        return proxyConnection;
    }
}
