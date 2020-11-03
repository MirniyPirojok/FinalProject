package by.borisov.restaurant.controller.command.usercommand;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.exception.ServiceException;
import by.borisov.restaurant.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static by.borisov.restaurant.model.dao.ColumnName.EMAIL;
import static by.borisov.restaurant.model.dao.ColumnName.LOGIN;
import static by.borisov.restaurant.model.dao.ColumnName.NAME;
import static by.borisov.restaurant.model.dao.ColumnName.PASSWORD;
import static by.borisov.restaurant.model.dao.ColumnName.PHONE;
import static by.borisov.restaurant.model.dao.ColumnName.SURNAME;

/**
 * Class allows the guest to sign up
 */

public class SignUpCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_ERROR_PARAM = "mes_err";
    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    /**
     * @param request from browser
     * @return String page
     */

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(LOGIN, request.getParameter(LOGIN));
        userParameters.put(EMAIL, request.getParameter(EMAIL));
        userParameters.put(PASSWORD, request.getParameter(PASSWORD));
        userParameters.put(PHONE, request.getParameter(PHONE));
        userParameters.put(NAME, request.getParameter(NAME));
        userParameters.put(SURNAME, request.getParameter(SURNAME));

        try {
            userServiceImpl.createUser(userParameters);
            HttpSession session = request.getSession();
            session.setAttribute("user", userParameters.get(LOGIN));
            page = PagePath.LOGIN_PAGE;
        } catch (ServiceException e) {
            request.setAttribute(MESSAGE_ERROR_PARAM, "Impossible to signUp");
            logger.warn("Error when SignUpCommand", e);
        }
        return page;
    }
}
