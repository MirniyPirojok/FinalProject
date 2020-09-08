package by.borisov.example.service;

public class UserService {
    private static final String ADMIN_LOGIN = "1";
    private static final String ADMIN_PASS = "1";

    private UserService() {
    }

    public static boolean checkUser(String enterLogin, String enterPass) {
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass);
    }
}
