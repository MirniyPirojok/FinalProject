package by.borisov.restaurant.model.dao;

import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.DaoException;

public interface UserDao {
    User findByLogin(String login) throws DaoException;
    User findByLoginAndPassword(String login, String password) throws DaoException;
}
