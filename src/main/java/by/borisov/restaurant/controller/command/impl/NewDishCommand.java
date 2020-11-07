package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.controller.command.ParameterName;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.service.DishService;
import by.borisov.restaurant.model.service.impl.DishServiceImpl;
import by.borisov.restaurant.util.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static by.borisov.restaurant.controller.command.ParameterName.FORM_PARAM_MESSAGE_ERROR;
import static by.borisov.restaurant.controller.command.ParameterName.FORM_PARAM_MESSAGE_SUCCESS;
import static by.borisov.restaurant.model.dao.ColumnName.COST;
import static by.borisov.restaurant.model.dao.ColumnName.NAME;

public class NewDishCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);
        if (!FormValidator.isPost(request)) {
            page = PagePath.NEW_DISH_PAGE;
        } else {
            Map<String, String> dishParameters = new HashMap<>();
            dishParameters.put(NAME, request.getParameter(NAME));
            dishParameters.put(COST, request.getParameter(COST));

            DishService dishService = new DishServiceImpl();
            try {
                dishService.createDish(dishParameters);
                logger.debug("Dish was created");
                request.setAttribute(FORM_PARAM_MESSAGE_SUCCESS, "New dish is created successfully");
                page = PagePath.NEW_DISH_PAGE;
            } catch (ServiceException e) {
                request.setAttribute(FORM_PARAM_MESSAGE_ERROR, "Can't create new dish");
                logger.error("Error. Dish wasn't created.", e);
                page = PagePath.NEW_DISH_PAGE;
            }
        }

        return page;
    }
}
