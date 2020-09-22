package by.borisov.webtask.controller.command.impl;

import by.borisov.webtask.controller.command.ActionCommand;
import by.borisov.webtask.entity.User;
import by.borisov.webtask.exception.ServiceException;
import by.borisov.webtask.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.webtask.controller.command.PagePath.MAIN_PAGE;

public class LoginCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        try {
            User user = userService.findUser(login, pass);
            if (user != null) {
                request.setAttribute("user", user.getLogin());
                page = MAIN_PAGE;
            } else {
                request.setAttribute("errorLoginMessage", "login error");
            }
        } catch (ServiceException e) {
            logger.info("User is not found.");
            request.setAttribute("errorLoginMessage", "login error");
        }
        return page;
    }
}
