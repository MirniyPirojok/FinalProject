package by.borisov.webtask.model.service;

import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    User findUser(String login, String password) throws ServiceException;
}
