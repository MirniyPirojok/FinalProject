package by.borisov.webtask.model.service;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.DaoException;
import by.borisov.webtask.exception.ServiceException;
import by.borisov.webtask.model.dao.EntityTransaction;
import by.borisov.webtask.model.dao.impl.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class allows to manage users.
 */

public class UserService {
    static Logger logger = LogManager.getLogger();

    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.error("Error during find all users transaction.", e);
            throw new ServiceException("Error during find all users transaction.", e);
        } finally {
            transaction.end();
        }
        return users;
    }

    public User findUser(String login, String password) throws ServiceException {
        User user;
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);

        try {
            user = userDao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.error("Error during transaction find user by login and password.", e);
            throw new ServiceException("Error during transaction find user by login and password.", e);
        } finally {
            transaction.end();
        }
        return user;
    }

}
