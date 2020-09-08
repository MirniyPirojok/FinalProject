package by.borisov.example.servlet;

import by.borisov.example.command.ActionCommand;
import by.borisov.example.command.CommandProvider;
import by.borisov.example.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.borisov.example.command.PagePath.INDEX_PAGE;

@WebServlet(urlPatterns = "/controller")
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<ActionCommand> commandOptional =
                CommandProvider.defineCommand(request.getParameter("command"));
        ActionCommand command = commandOptional.orElseThrow(IllegalArgumentException::new);

        String page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullPage"));
            response.sendRedirect(request.getContextPath() + INDEX_PAGE);
        }
    }
}
