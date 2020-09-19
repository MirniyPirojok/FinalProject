package by.borisov.webtask.model.dao;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.DaoException;

public interface UserDao extends BaseDao<User> {
    User findByLogin(String login) throws DaoException;
}
