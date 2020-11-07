package by.borisov.restaurant.model.service;

import by.borisov.restaurant.entity.Dish;
import by.borisov.restaurant.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface DishService {
    List<Dish> findAllDishes() throws ServiceException;

    Dish findDish(String name) throws ServiceException;

    boolean createDish(Map<String, String> dishParameters) throws ServiceException;
}
