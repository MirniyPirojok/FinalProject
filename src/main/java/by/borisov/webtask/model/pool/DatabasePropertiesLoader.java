package by.borisov.webtask.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for database properties loading
 */

class DatabasePropertiesLoader {
    static Logger logger = LogManager.getLogger();
    private static final String DB_PROPERTIES_PATH = "database.properties";

    static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream databaseProperties =
                DatabasePropertiesLoader.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_PATH);
        try {
            properties.load(databaseProperties);
        } catch (IOException e) {
            logger.fatal("Property file loading error.", e);
        }
        logger.info("Database properties were loaded.");
        return properties;
    }
}
