package by.borisov.example.servlet;

import by.borisov.example.command.ActionCommand;
import by.borisov.example.command.factory.ActionFactory;
import by.borisov.example.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.borisov.example.command.PagePath.INDEX;

@WebServlet(urlPatterns = "/controller")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        String page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = INDEX;
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullPage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
