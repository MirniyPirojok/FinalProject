package by.borisov.webtask.model.dao;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.DaoException;

public interface UserDao {
    User findByLogin(String login) throws DaoException;
    User findByLoginAndPassword(String login, String password) throws DaoException;
}
