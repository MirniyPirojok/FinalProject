package by.borisov.webtask.model.service.impl;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.DaoException;
import by.borisov.webtask.exception.ServiceException;
import by.borisov.webtask.model.dao.EntityTransaction;
import by.borisov.webtask.model.dao.impl.UserDaoImpl;
import by.borisov.webtask.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class allows to manage users.
 */

public class UserServiceImp implements UserService {
    static Logger logger = LogManager.getLogger();

    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        EntityTransaction action = new EntityTransaction();
        try {
            action.begin(userDaoImpl);
            users = userDaoImpl.findAll();
        } catch (DaoException e) {
            logger.error("Error during find all users transaction.", e);
            throw new ServiceException("Error during find all users transaction.", e);
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
            logger.error("Error during transaction find user by login and password.", e);
            throw new ServiceException("Error during transaction find user by login and password.", e);
        } finally {
            action.end();
        }
        return user;
    }
}
