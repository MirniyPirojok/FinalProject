package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.ParameterName;
import by.borisov.restaurant.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ViewDishesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);

        return PagePath.DISHES_PAGE;
    }
}
