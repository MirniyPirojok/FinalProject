package by.borisov.example.command.impl;

import by.borisov.example.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.example.command.PagePath.INDEX;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = INDEX;
        request.getSession().invalidate();
        return page;
    }
}
