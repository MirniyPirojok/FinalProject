package by.borisov.restaurant.controller;

import by.borisov.restaurant.controller.command.ActionCommand;
import by.borisov.restaurant.controller.command.CommandProvider;
import by.borisov.restaurant.controller.command.FormParameterName;
import by.borisov.restaurant.controller.command.PagePath;
import by.borisov.restaurant.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.borisov.restaurant.controller.command.FormParameterName.GET;
import static by.borisov.restaurant.controller.command.FormParameterName.POST;
import static by.borisov.restaurant.controller.command.FormParameterName.REDIRECT_SECURE;
import static by.borisov.restaurant.controller.command.FormParameterName.TYPE_METHOD;

@WebServlet(urlPatterns = {"/do"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqParameter = request.getParameter(FormParameterName.FORM_PARAM_COMMAND);
        String nameCommand = reqParameter.toUpperCase();
        ActionCommand command = CommandProvider.defineCommand(nameCommand);

        String page = command.execute(request);
        logger.debug(String.format("Page in controller after command: %s", page));

        if (page != null) {
            HttpSession session = request.getSession(true);
            String typeMethod = (String) request.getAttribute(TYPE_METHOD);
            if (typeMethod.equals(GET)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                logger.debug(String.format("GET method in controller. Page to go: %s", page));
                dispatcher.forward(request, response);
            } else {
                session.setAttribute(REDIRECT_SECURE, POST);
                String redirectPath = CommandHelper.findRedirectCommand(nameCommand);
                response.sendRedirect(request.getContextPath() + redirectPath);
                logger.debug("Post method in controller");

            }
        } else {
            page = PagePath.ERROR_PAGE;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
