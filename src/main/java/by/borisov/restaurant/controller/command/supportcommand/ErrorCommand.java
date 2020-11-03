package by.borisov.restaurant.controller.command.supportcommand;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.restaurant.controller.command.FormParameterName.GET;
import static by.borisov.restaurant.controller.command.FormParameterName.TYPE_METHOD;

/**
 * Class display Error page
 */

public class ErrorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(TYPE_METHOD, GET);
        return PagePath.ERROR_PAGE;
    }
}
