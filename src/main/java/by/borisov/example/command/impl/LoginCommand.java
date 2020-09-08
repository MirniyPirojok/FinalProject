package by.borisov.example.command.impl;

import by.borisov.example.command.ActionCommand;
import by.borisov.example.service.UserService;
import by.borisov.example.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.example.command.PagePath.LOGIN;
import static by.borisov.example.command.PagePath.MAIN;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        if (UserService.checkUser(login, pass)) {
            request.setAttribute("user", login);
            page = MAIN;
        } else {
            request.setAttribute("errorLoginMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = LOGIN;
        }
        return page;
    }
}
