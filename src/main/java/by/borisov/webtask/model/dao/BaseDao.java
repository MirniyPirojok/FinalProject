package by.borisov.webtask.model.dao;

import by.borisov.webtask.entity.Entity;
import by.borisov.webtask.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * This interface declares common methods for all entities which interact with database
 *
 * @param <T> the type of entities associated with database tables
 */
public interface BaseDao<T extends Entity> {
    static Logger logger = LogManager.getLogger();

    List<T> findAll() throws DaoException;

    T findEntityById(int id) throws DaoException;

    boolean delete(T t) throws DaoException;

    boolean delete(int id) throws DaoException;

    boolean create(T t) throws DaoException;

    T update(T t) throws DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Statement wasn't closed", e);
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close(); //return connection to the pool
            }
        } catch (SQLException e) {
            logger.error("Connection wasn't returned to the pool.", e);
        }
    }
}
