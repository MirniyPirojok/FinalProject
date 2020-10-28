package by.borisov.webtask.controller.command;

import by.borisov.webtask.controller.command.impl.LoginCommand;
import by.borisov.webtask.controller.command.impl.LogoutCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
