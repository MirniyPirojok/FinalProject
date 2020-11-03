package by.borisov.restaurant.model.service;

import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    User findUser(String login, String password) throws ServiceException;
}
