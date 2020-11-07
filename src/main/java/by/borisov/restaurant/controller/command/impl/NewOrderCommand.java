package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.ParameterName;
import by.borisov.restaurant.entity.Dish;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.service.DishService;
import by.borisov.restaurant.model.service.impl.DishServiceImpl;
import by.borisov.restaurant.util.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.borisov.restaurant.controller.command.PagePath.NEW_ORDER_PAGE;

public class NewOrderCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);
        if (!FormValidator.isPost(request)) {
            DishService dishService = new DishServiceImpl();
            try {
                List<Dish> dishes = dishService.findAllDishes();
                request.setAttribute(ParameterName.FORM_PARAM_DISHES, dishes);
                logger.debug("All dishes are found.");
                page = NEW_ORDER_PAGE;
            } catch (ServiceException e) {
                logger.error("Error. Impossible to show all dishes.", e);
                request.setAttribute(ParameterName.FORM_PARAM_MESSAGE_ERROR, "Error. Impossible to show all dishes");
                page = NEW_ORDER_PAGE;
            }
        } else {
            page = NEW_ORDER_PAGE;
        }
        return page;
    }
}
