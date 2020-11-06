package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.controller.command.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class NewDishCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);
        return PagePath.NEW_DISH_PAGE;
    }
}
