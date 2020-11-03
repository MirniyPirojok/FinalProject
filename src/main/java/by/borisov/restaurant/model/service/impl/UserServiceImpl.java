package by.borisov.restaurant.model.service.impl;

import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.DaoException;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.dao.EntityTransaction;
import by.borisov.restaurant.model.dao.impl.UserDaoImpl;
import by.borisov.restaurant.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static by.borisov.restaurant.model.dao.ColumnName.EMAIL;
import static by.borisov.restaurant.model.dao.ColumnName.LOGIN;
import static by.borisov.restaurant.model.dao.ColumnName.NAME;
import static by.borisov.restaurant.model.dao.ColumnName.PASSWORD;
import static by.borisov.restaurant.model.dao.ColumnName.PHONE;
import static by.borisov.restaurant.model.dao.ColumnName.SURNAME;

/**
 * Class allows to manage users.
 */

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static final String ROLE_USER = "user";

    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        EntityTransaction action = new EntityTransaction();
        try {
            action.begin(userDaoImpl);
            users = userDaoImpl.findAll();
        } catch (DaoException e) {
            logger.error("Error during searching all users.", e);
            throw new ServiceException("Error during searching all users.", e);
        } finally {
            action.end();
        }
        return users;
    }

    public User findUser(String login, String password) throws ServiceException {
        User user;
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        EntityTransaction action = new EntityTransaction();
        try {
            action.begin(userDaoImpl);
            user = userDaoImpl.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.error("Error during searching user by login and password.", e);
            throw new ServiceException("Error during searching user by login and password.", e);
        } finally {
            action.end();
        }
        return user;
    }

    public boolean createUser(Map<String, String> userParameters) throws ServiceException {
        boolean isUserCreated = false;

        User user = new User();
        user.setRole(ROLE_USER);
        user.setLogin(userParameters.get(LOGIN));
        user.setEmail(userParameters.get(EMAIL));
        user.setPassword(userParameters.get(PASSWORD));
        user.setPhone(userParameters.get(PHONE));
        user.setName(userParameters.get(NAME));
        user.setSurname(userParameters.get(SURNAME));

        UserDaoImpl userDaoImpl = new UserDaoImpl();
        EntityTransaction action = new EntityTransaction();

        try {
            action.begin(userDaoImpl);
            isUserCreated = userDaoImpl.insert(user);
            logger.info("User account was created");
        } catch (DaoException e) {
            logger.error("Error during creating user", e);
            throw new ServiceException(e);
        } finally {
            action.end();
        }
        return isUserCreated;
    }
}
