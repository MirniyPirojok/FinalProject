package by.borisov.restaurant.controller.command.supportcommand;

import by.borisov.restaurant.controller.command.ActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.borisov.restaurant.controller.command.FormParameterName.FORM_PARAM_LOCALE;
import static by.borisov.restaurant.controller.command.FormParameterName.GET;
import static by.borisov.restaurant.controller.command.FormParameterName.LANGUAGE_EN;
import static by.borisov.restaurant.controller.command.FormParameterName.LANGUAGE_RU;
import static by.borisov.restaurant.controller.command.FormParameterName.LOCALE_EN;
import static by.borisov.restaurant.controller.command.FormParameterName.LOCALE_RU;
import static by.borisov.restaurant.controller.command.FormParameterName.SAVED_PAGE;
import static by.borisov.restaurant.controller.command.FormParameterName.TYPE_METHOD;

/**
 * The class changes the locale.
 */
public class ChangeLocaleCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger();
    private String locale = LOCALE_EN;

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(TYPE_METHOD, GET);
        if (request.getParameter(LANGUAGE_EN) != null) {
            locale = LOCALE_EN;
            logger.info("Locale was changed to en");
        }
        if (request.getParameter(LANGUAGE_RU) != null) {
            locale = LOCALE_RU;
            logger.info("Locale was changed to ru");
        }
        HttpSession session = request.getSession();
        session.setAttribute(FORM_PARAM_LOCALE, locale);
        String page = (String) session.getAttribute(SAVED_PAGE);
        logger.debug(String.format("Page to go: %s", page));

        return page;
    }
}
