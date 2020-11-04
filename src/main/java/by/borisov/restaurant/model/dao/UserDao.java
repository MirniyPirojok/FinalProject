package by.borisov.restaurant.model.dao;

import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.DaoException;

public interface UserDao {
    User findByEmailAndPassword(String email, String password) throws DaoException;
}
