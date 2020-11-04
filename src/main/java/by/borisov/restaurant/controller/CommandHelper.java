package by.borisov.restaurant.controller;

public class CommandHelper {
    private static final String POST_COMMAND_SIGN_UP = "sign_up";
    private static final String GET_COMMAND_LOGIN = "do?command=login";
    private static final String GET_COMMAND_ERROR = "do?command=error";

    public static String findRedirectCommand(String nameCommand) {
        String redirectCommand;
        switch (nameCommand) {
            case (POST_COMMAND_SIGN_UP):
                redirectCommand = GET_COMMAND_LOGIN;
                break;
            default:
                redirectCommand = GET_COMMAND_ERROR;
        }
        return redirectCommand;
    }
}


