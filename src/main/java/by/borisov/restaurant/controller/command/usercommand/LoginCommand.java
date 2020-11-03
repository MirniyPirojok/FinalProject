package by.borisov.restaurant.controller.command.usercommand;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.entity.User;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.borisov.restaurant.controller.command.PagePath.MAIN_PAGE;

/**
 * Class allows to log in
 */

public class LoginCommand implements ActionCommand {

    static Logger logger = LogManager.getLogger();
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    /**
     *
     * @param request from browser
     * @return String page
     */

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);

        try {
//            pass = EncryptionPassword.encrypt(pass);
            User user = userServiceImpl.findUser(login, pass);
            HttpSession session = request.getSession();
            if (user != null) {
                session.setAttribute("user", user.getLogin());
                page = MAIN_PAGE;
            } else {
                session.setAttribute("errorLoginMessage", "login error");
            }
        } catch (ServiceException e) {
            logger.error("Incorrect data.", e);
            request.setAttribute("errorLoginMessage", "login error");
            page = PagePath.LOGIN_PAGE;
        }
        return page;
    }
}
