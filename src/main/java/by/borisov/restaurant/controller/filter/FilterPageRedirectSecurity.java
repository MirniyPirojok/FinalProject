package by.borisov.restaurant.controller.filter;

import by.borisov.restaurant.controller.command.FormParameterName;
import by.borisov.restaurant.controller.command.PagePath;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class prevents direct browser access to pages
 * But it allows to access from servlet
 */
@WebFilter(urlPatterns = {"/pages/*"})
public class FilterPageRedirectSecurity implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Method is called every time when filter accessed
     *
     * @param servletRequest  current request
     * @param servletResponse current response
     * @param filterChain     list of all filters
     * @throws IOException      if happens IOException
     * @throws ServletException if happens ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute(FormParameterName.REDIRECT_SECURE) == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE);
        } else {
            session.setAttribute(FormParameterName.REDIRECT_SECURE, null);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
