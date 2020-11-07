package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.controller.command.ParameterName;
import by.borisov.restaurant.entity.Dish;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.service.DishService;
import by.borisov.restaurant.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewDishesCommand implements ActionCommand {
    Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);

        DishService dishService = new DishServiceImpl();
        try {
            List<Dish> dishes = dishService.findAllDishes();
            request.setAttribute(ParameterName.FORM_PARAM_DISHES, dishes);
            logger.debug("All dishes are found.");
        } catch (ServiceException e) {
            logger.error("Error. Impossible to show all dishes.", e);
            request.setAttribute(ParameterName.FORM_PARAM_MESSAGE_ERROR, "Error. Impossible to show all dishes");
        }

        return PagePath.DISHES_PAGE;
    }
}
