package by.borisov.restaurant.model.service;

import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    User findUser(String login, String password) throws ServiceException;

    boolean createUser(Map<String, String> userParameters) throws ServiceException;
}
