package by.borisov.restaurant.controller.command;

public class CommandProvider {

    public static ActionCommand defineCommand(String command) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            return CommandType.ERROR.getCommand();
        }
        return commandType.getCommand();
    }
}
