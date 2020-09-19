package by.borisov.webtask.model.dao.impl;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.DaoException;
import by.borisov.webtask.model.dao.UserDao;
import by.borisov.webtask.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.borisov.webtask.model.dao.ColumnName.EMAIL;
import static by.borisov.webtask.model.dao.ColumnName.ID_USER;
import static by.borisov.webtask.model.dao.ColumnName.LOGIN;
import static by.borisov.webtask.model.dao.ColumnName.PASSWORD;
import static by.borisov.webtask.model.dao.ColumnName.ROLE;

public class UserDaoImpl implements UserDao {
    static Logger logger = LogManager.getLogger();

    private static final String SELECT_USER_BY_LOGIN =
            "SELECT iduser, role, email, password FROM users WHERE login=?";
    public static final String SELECT_ALL_USERS =
            "SELECT iduser, role, login, email, password FROm users";

    @Override
    public User findByLogin(String login) throws DaoException {
        User user = new User();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(ID_USER));
                user.setRole(resultSet.getString(ROLE));
                user.setLogin(resultSet.getString(LOGIN));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
            }
        } catch (SQLException e) {
            logger.error(String.format("Exception during user searching by login %s.", login), e);
            throw new DaoException("Exception during user by login searching.", e);
        } finally {
            close(statement);
            close(connection);
        }

        logger.info(String.format("Searching of user with login %s was finished.", login));
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID_USER));
                user.setRole(resultSet.getString(ROLE));
                user.setLogin(resultSet.getString(LOGIN));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Exception during searching all users", e);
            throw new DaoException("Exception during searching all users", e);
        } finally {
            close(statement);
            close(connection);
        }

        logger.info("All users searching is finished.");
        return users;
    }

    @Override
    public User findEntityById(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        return false;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }
}
