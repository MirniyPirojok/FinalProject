package by.borisov.webtask.model.dao.impl;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.DaoException;
import by.borisov.webtask.model.dao.UserDao;

import java.util.List;

public class UserDaoImpl implements UserDao{

    @Override
    public boolean checkLoginAndPassword(String login, String password) throws DaoException {
        return false;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
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
