package by.borisov.example.command.factory;

import by.borisov.example.command.ActionCommand;
import by.borisov.example.command.CommandType;
import by.borisov.example.command.impl.EmptyCommand;
import by.borisov.example.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter("command");
        if (action == null || action.isBlank()) {
            return current;
        }
        try {
            CommandType currentType = CommandType.valueOf(action.toUpperCase());
            current = currentType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongAction"));
        }
        return current;
    }
}
