package by.borisov.restaurant.util;

import by.borisov.restaurant.controller.command.FormParameterName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class validates form
 */

public class FormValidator {
    public static boolean isPost(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase(FormParameterName.POST);
    }
}
