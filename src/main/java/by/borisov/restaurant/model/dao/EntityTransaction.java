package by.borisov.restaurant.model.dao;

import by.borisov.restaurant.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    static Logger logger = LogManager.getLogger();
    private Connection connection;

    public void begin(AbstractDao dao) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        dao.setConnection(connection);
        logger.info(String.format("%s has a connection: %s", dao, connection));
    }

    public void begin(AbstractDao... daos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.warn("Can't set autocommit to false", e);
        }

        for (AbstractDao dao : daos) {
            dao.setConnection(connection);
            logger.info(String.format("%s has a connection: %s", dao, connection));
        }
    }

    public void end() {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
                connection.close();
            } catch (SQLException e) {
                logger.error("Can't return connection to pool.", e);
            }
        }
        connection = null;
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.warn("Can't commit connection.", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.warn("Can't rollback connection", e);
        }
    }
}
