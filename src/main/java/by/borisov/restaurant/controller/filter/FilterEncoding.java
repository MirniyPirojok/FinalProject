package by.borisov.restaurant.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Class checks and sets the required encoding
 */

@WebFilter(urlPatterns = {"/*"}, initParams =
        {@WebInitParam(name = "encoding", value = "UTF-8", description = "encoding param")})
public class FilterEncoding implements Filter {
    private String code;


    /**
     * Method initializes required data
     *
     * @param filterConfig set filter params
     */
    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("encoding");
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
        String currentEncoding = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(currentEncoding)) {
            servletRequest.setCharacterEncoding(code);
        }
        currentEncoding = servletResponse.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(currentEncoding)) {
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Method is called when the filter terminates
     */
    @Override
    public void destroy() {
        code = null;
    }
}
