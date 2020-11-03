package by.borisov.restaurant.controller.command;

import by.borisov.restaurant.controller.command.supportcommand.ChangeLocaleCommand;
import by.borisov.restaurant.controller.command.supportcommand.ErrorCommand;
import by.borisov.restaurant.controller.command.usercommand.LoginCommand;
import by.borisov.restaurant.controller.command.usercommand.LogoutCommand;
import by.borisov.restaurant.controller.command.usercommand.SignUpCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGN_UP(new SignUpCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    ERROR(new ErrorCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
