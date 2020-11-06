package by.borisov.restaurant.controller.command;

import by.borisov.restaurant.controller.command.impl.ChangeLocaleCommand;
import by.borisov.restaurant.controller.command.impl.AddOrderCommand;
import by.borisov.restaurant.controller.command.impl.ErrorCommand;
import by.borisov.restaurant.controller.command.impl.LoginCommand;
import by.borisov.restaurant.controller.command.impl.LogoutCommand;
import by.borisov.restaurant.controller.command.impl.SignUpCommand;
import by.borisov.restaurant.controller.command.impl.ViewDishesCommand;
import by.borisov.restaurant.controller.command.impl.ViewOrdersCommand;

public enum CommandType {
    VIEW_DISHES(new ViewDishesCommand()),
    ADD_ORDER(new AddOrderCommand()),
    VIEW_ORDERS(new ViewOrdersCommand()),
    LOGIN(new LoginCommand()),
    SIGN_UP(new SignUpCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    ERROR(new ErrorCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
