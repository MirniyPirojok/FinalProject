package by.borisov.webtask.model.dao;

import by.borisov.webtask.entity.Entity;
import by.borisov.webtask.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao<T extends Entity> {
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
            //log
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close(); //or connection return code to the pool
            }
        } catch (SQLException e) {
            //log
        }
    }
}
