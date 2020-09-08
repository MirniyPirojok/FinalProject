package by.borisov.example.command.impl;

import by.borisov.example.command.ActionCommand;
import by.borisov.example.service.UserService;
import by.borisov.example.resource.MessageManager;
import by.borisov.example.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.example.command.PagePath.LOGIN_PAGE;
import static by.borisov.example.command.PagePath.MAIN_PAGE;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        if (userService.checkUser(login, pass)) {
            request.setAttribute("user", login);
            page = MAIN_PAGE;
        } else {
            request.setAttribute("errorLoginMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = LOGIN_PAGE;
        }
        return page;
    }
}
