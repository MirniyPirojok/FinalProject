package by.borisov.restaurant.controller.command.impl;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.controller.command.ParameterName;
import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.dao.ColumnName;
import by.borisov.restaurant.model.service.impl.UserServiceImpl;
import by.borisov.restaurant.util.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.borisov.restaurant.controller.command.PagePath.LOGIN_PAGE;
import static by.borisov.restaurant.controller.command.PagePath.MAIN_PAGE;
import static by.borisov.restaurant.controller.command.ParameterName.FORM_PARAM_ERROR_MAIL_OR_LOGIN;

/**
 * Class allows to log in
 */

public class LoginCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();
    UserServiceImpl userServiceImpl = new UserServiceImpl();


    /**
     * @param request from browser
     * @return String page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(ParameterName.TYPE_METHOD, ParameterName.GET);
        if (!FormValidator.isPost(request)) {
            page = PagePath.LOGIN_PAGE;
        } else {
            try {
                String email = request.getParameter(ColumnName.EMAIL);
                String password = request.getParameter(ColumnName.PASSWORD);
                User user = userServiceImpl.findUser(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute(ParameterName.FORM_PARAM_USER_NAME, user.getName());
                    page = MAIN_PAGE;
                    logger.debug(String.format("User %s was found. Go to main page.", email));
                } else {
                    request.setAttribute(FORM_PARAM_ERROR_MAIL_OR_LOGIN, "Wrong email or login.");
                    page = LOGIN_PAGE;
                    logger.debug(String.format("E-mail %s was not found. Go to login page.", email));
                }
            } catch (ServiceException e) {
                logger.error("Incorrect data.", e);
                page = PagePath.LOGIN_PAGE;
            }
        }
        logger.debug(String.format("Page after login command: %s", page));

        return page;
    }
}
