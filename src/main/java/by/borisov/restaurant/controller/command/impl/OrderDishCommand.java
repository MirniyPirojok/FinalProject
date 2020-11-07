package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.controller.command.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class OrderDishCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);

        page = "/do?command=new_order";
        return page;
    }
}
