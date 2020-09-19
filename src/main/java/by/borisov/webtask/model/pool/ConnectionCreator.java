package by.borisov.webtask.model.pool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {
    private static final String URL = "db.url";

    private ConnectionCreator() {
    }

    static ProxyConnection createConnection(Properties properties) throws SQLException {
        String url = properties.getProperty(URL);
        return new ProxyConnection(DriverManager.getConnection(url, properties));
    }
}
