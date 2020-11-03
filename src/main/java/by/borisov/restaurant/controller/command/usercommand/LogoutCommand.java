package by.borisov.restaurant.controller.command.usercommand;

import by.borisov.restaurant.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.restaurant.controller.command.PagePath.INDEX_PAGE;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return INDEX_PAGE;
    }
}
