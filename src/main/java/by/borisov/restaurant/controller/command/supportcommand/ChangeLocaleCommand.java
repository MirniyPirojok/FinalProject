package by.borisov.restaurant.controller.command.supportcommand;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.borisov.restaurant.controller.command.FormParameterName.*;

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
        return PagePath.INDEX_PAGE;
    }
}
