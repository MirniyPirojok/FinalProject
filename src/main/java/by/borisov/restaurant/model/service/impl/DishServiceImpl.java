package by.borisov.restaurant.model.service.impl;

import by.borisov.restaurant.entity.Dish;
import by.borisov.restaurant.exception.DaoException;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.dao.DishDao;
import by.borisov.restaurant.model.dao.EntityTransaction;
import by.borisov.restaurant.model.dao.impl.DishDaoImpl;
import by.borisov.restaurant.model.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.borisov.restaurant.model.dao.ColumnName.COST;
import static by.borisov.restaurant.model.dao.ColumnName.NAME;

public class DishServiceImpl implements DishService {
    static Logger logger = LogManager.getLogger();


    @Override
    public List<Dish> findAllDishes() throws ServiceException {
        List<Dish> dishes;
        DishDaoImpl dishDao = new DishDaoImpl();
        EntityTransaction action = new EntityTransaction();
        try {
            action.begin(dishDao);
            dishes = dishDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            action.end();
        }
        return dishes;
    }

    @Override
    public Dish findDish(String name) throws ServiceException {
        return null;
    }

    @Override
    public boolean createDish(Map<String, String> dishParameters) throws ServiceException {
        boolean isDishCreated;

        Dish dish = new Dish();
        dish.setName(dishParameters.get(NAME));
        BigDecimal cost = new BigDecimal(dishParameters.get(COST));
        dish.setCost(cost);

        DishDaoImpl dishDao = new DishDaoImpl();
        EntityTransaction action = new EntityTransaction();

        try {
            action.begin(dishDao);
            isDishCreated = dishDao.insert(dish);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            action.end();
        }
        return isDishCreated;
    }
}
