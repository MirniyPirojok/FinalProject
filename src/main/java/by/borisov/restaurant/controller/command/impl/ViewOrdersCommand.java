package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.ParameterName;
import by.borisov.restaurant.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ViewOrdersCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);
        return PagePath.ORDERS_PAGE;
    }
}
