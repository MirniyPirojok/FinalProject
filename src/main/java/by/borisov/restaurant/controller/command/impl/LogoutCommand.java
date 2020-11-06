package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.restaurant.controller.command.PagePath.LOGIN_PAGE;

/**
 * Class for log out
 */

public class LogoutCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);
        request.getSession().invalidate();
        logger.debug("Session is invalidate. Return to index page");
        page = LOGIN_PAGE;
        return page;
    }
}
