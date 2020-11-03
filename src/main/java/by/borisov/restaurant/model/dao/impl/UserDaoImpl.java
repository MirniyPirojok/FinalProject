package by.borisov.restaurant.model.dao.impl;

import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.DaoException;
import by.borisov.restaurant.model.dao.AbstractDao;
import by.borisov.restaurant.model.dao.ColumnName;
import by.borisov.restaurant.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    static Logger logger = LogManager.getLogger();

    public static final String SELECT_ALL_USERS =
            "SELECT iduser, login, email, password, role, phone, name, surname FROM users";
    private static final String SELECT_USER_BY_LOGIN =
            "SELECT iduser, login, email, password, role, phone, name, surname FROM users WHERE login=?";
    private static final String SELECT_USER_BY_ID =
            "SELECT iduser, login, email, password, role, phone, name, surname FROM users WHERE iduser=?";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT iduser, login, email, password, role, phone, name, surname FROM users WHERE login=? and password=?";
    private static final String INSERT_USER =
            "INSERT INTO users (login, email, password, role, phone, name, surname) VALUES (?,?,?,?,?,?,?)";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = setUserParams(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Exception when searching for all user.", e);
            throw new DaoException("Exception when searching for all user.", e);
        } finally {
            close(statement);
        }

        logger.info("The search for all users is completed.");
        return users;
    }

    public User findByLogin(String login) throws DaoException {
        User user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = setUserParams(resultSet);
            }
        } catch (SQLException e) {
            logger.error(String.format("Exception when searching for a user by login %s.", login), e);
            throw new DaoException(String.format("Exception when searching for a user by login %s.", login), e);
        } finally {
            close(statement);
        }
        logger.info(String.format("The search for user with login %s is completed.", login));
        return user;
    }

    @Override
    public User findById(long id) throws DaoException {
        User user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = setUserParams(resultSet);
            }
        } catch (SQLException e) {
            logger.error(String.format("Exception when searching for a user by id %d.", id), e);
            throw new DaoException(String.format("Exception when searching for a user by id %d.", id), e);
        } finally {
            close(statement);
        }
        logger.info(String.format("The search for user with id %d is completed.", id));
        return user;
    }

    public User findByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = setUserParams(resultSet);
                logger.info(String.format("User %s was found", login));
            } else {
                logger.info("User with such login and password wasn't found.");
            }
        } catch (SQLException e) {
            logger.error("Exception when searching for a user by login and password", e);
            throw new DaoException("Exception when searching for a user by login and password", e);
        } finally {
            close(statement);
        }

        return user;
    }

    private User setUserParams(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.ID_USER));
        user.setRole(resultSet.getString(ColumnName.ROLE));
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setPhone(resultSet.getString(ColumnName.PHONE));
//        user.setPassword(resultSet.getString(ColumnName.PASSWORD));
        return user;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return false;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        boolean flag;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getName());
            statement.setString(7, user.getSurname());
            flag = (statement.executeUpdate() == 7);
        } catch (SQLException e) {
            logger.info("Exception when inserting a user", e);
            throw new DaoException("Exception when inserting a user.", e);
        } finally {
            close(statement);
        }
        return flag;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }

}
