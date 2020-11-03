package by.borisov.restaurant.model.dao;

import by.borisov.restaurant.entity.Entity;
import by.borisov.restaurant.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * This abstract class is for organizing a logic level of DAO.
 * Class has common methods for all DAOs, sets connection for DAOs,
 * that allows to create just one connection for a set of methods (transaction).
 *
 * @param <T> the type of entities associated with database tables.
 */
public abstract class AbstractDao<T extends Entity> {
    protected Connection connection;
    static Logger logger = LogManager.getLogger();

    public abstract List<T> findAll() throws DaoException;

    public abstract T findById(long id) throws DaoException;

    public abstract boolean delete(T t) throws DaoException;

    public abstract boolean delete(long id) throws DaoException;

    public abstract boolean insert(T t) throws DaoException;

    public abstract T update(T t) throws DaoException;

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.warn("Statement wasn't closed.", e);
        }
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }
}