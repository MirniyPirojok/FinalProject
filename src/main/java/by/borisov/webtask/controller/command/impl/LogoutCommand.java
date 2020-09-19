package by.borisov.webtask.controller.command.impl;

import by.borisov.webtask.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.webtask.controller.command.PagePath.INDEX_PAGE;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return INDEX_PAGE;
    }
}
