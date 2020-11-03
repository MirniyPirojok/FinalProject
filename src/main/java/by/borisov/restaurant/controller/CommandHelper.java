package by.borisov.restaurant.controller;

public class CommandHelper {
    private static final String ATTRIBUTE_USER_KEY = "user";
    private static final double FLOATING_ONE_NUMBER = 1.0;
    private static final String POST_COMMAND_SIGN_UP = "SIGN_UP";
    private static final String POST_COMMAND_DELETE_TICKET_OFFICE = "DELETE_TICKET_OFFICE";
    private static final String POST_COMMAND_CREATE_TICKET_OFFICE = "CREATE_TICKET_OFFICE";
    private static final String POST_COMMAND_BUY_TICKET = "BUY_TICKET";
    private static final String POST_COMMAND_CREATE_EVENT = "CREATE_EVENT";
    private static final String POST_COMMAND_DELETE_EVENT = "DELETE_EVENT";
    private static final String POST_COMMAND_CREATE_TICKET = "CREATE_TICKET";
    private static final String POST_COMMAND_DELETE_TICKET = "DELETE_TICKET";
    private static final String GET_COMMAND_LOGIN = "do?command=Login";
    private static final String GET_COMMAND_PROFILE = "do?command=Profile";
    private static final String GET_COMMAND_EDIT_EVENT = "do?command=Edit_event";
    private static final String GET_COMMAND_EDIT_TICKET = "do?command=Edit_ticket";
    private static final String GET_COMMAND_EDIT_TICKET_OFFICE = "do?command=Edit_ticket_office";
    private static final String GET_COMMAND_ERROR = "do?command=Error";

    public static String findRedirectCommand(String nameCommand) {
        String redirectCommand;
        switch (nameCommand) {
            case (POST_COMMAND_SIGN_UP):
                redirectCommand = GET_COMMAND_LOGIN;
                break;
            case (POST_COMMAND_DELETE_TICKET_OFFICE):
            case (POST_COMMAND_CREATE_TICKET_OFFICE):
                redirectCommand = GET_COMMAND_EDIT_TICKET_OFFICE;
                break;
            case (POST_COMMAND_BUY_TICKET):
                redirectCommand = GET_COMMAND_PROFILE;
                break;
            case (POST_COMMAND_CREATE_EVENT):
            case (POST_COMMAND_DELETE_EVENT):
                redirectCommand = GET_COMMAND_EDIT_EVENT;
                break;
            case (POST_COMMAND_CREATE_TICKET):
            case (POST_COMMAND_DELETE_TICKET):
                redirectCommand = GET_COMMAND_EDIT_TICKET;
                break;
            default:
                redirectCommand = GET_COMMAND_ERROR;
        }
        return redirectCommand;
    }
}


