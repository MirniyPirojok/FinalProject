package by.borisov.example.command;

import by.borisov.example.command.impl.LoginCommand;
import by.borisov.example.command.impl.LogoutCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
