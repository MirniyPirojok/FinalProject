package by.borisov.restaurant.model.dao.impl;

import by.borisov.restaurant.entity.Dish;
import by.borisov.restaurant.exception.DaoException;
import by.borisov.restaurant.model.dao.AbstractDao;
import by.borisov.restaurant.model.dao.ColumnName;
import by.borisov.restaurant.model.dao.DishDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for making query about dish to DB
 */

public class DishDaoImpl extends AbstractDao<Dish> implements DishDao {
    static Logger logger = LogManager.getLogger();

    private static final String INSERT_DISH =
            "INSERT INTO dishes(name,cost) VALUES (?,?)";
    private static final String SELECT_ALL_DISHES =
            "SELECT id_dish, name, cost FROM dishes";

    @Override
    public boolean insert(Dish dish) throws DaoException {
        boolean isInserted;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_DISH);
            statement.setString(1, dish.getName());
            statement.setBigDecimal(2, dish.getCost());
            isInserted = (statement.executeUpdate() == 2);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return isInserted;
    }

    @Override
    public List<Dish> findAll() throws DaoException {
        List<Dish> dishes = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(SELECT_ALL_DISHES);
            while (resultset.next()) {
                Dish dish = setDishParams(resultset);
                dishes.add(dish);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }

        return dishes;
    }

    private Dish setDishParams(ResultSet resultset) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultset.getInt(ColumnName.ID_DISH));
        dish.setName(resultset.getString(ColumnName.NAME));
        dish.setCost(resultset.getBigDecimal(ColumnName.COST));
        return dish;
    }

    @Override
    public Dish findById(long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Dish dish) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return false;
    }

    @Override
    public Dish update(Dish dish) throws DaoException {
        return null;
    }
}
