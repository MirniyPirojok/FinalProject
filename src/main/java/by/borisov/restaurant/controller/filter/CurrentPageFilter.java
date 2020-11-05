package by.borisov.restaurant.controller.filter;

import by.borisov.restaurant.controller.command.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.borisov.restaurant.controller.command.FormParameterName.SAVED_PAGE;

@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {
    static Logger logger = LogManager.getLogger();
    private static final String REFERER = "referer";
    private static final String REGEX_DO="/do";
    private static final String REGEX_PATH = "/do.+|/do";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        String url = request.getHeader(REFERER);
        String path = substringPathWithRegex(url, session);
        session.setAttribute(SAVED_PAGE, path);
        logger.debug(String.format("Page is filtered (url: %s, substringPathWithRegex %s)", url, path));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String substringPathWithRegex(String url, HttpSession session) {
        Pattern pattern = Pattern.compile(REGEX_PATH);
        String path = null;
        if (url != null) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                if ((matcher.group().matches(REGEX_DO))) {
                    path = (String) session.getAttribute(SAVED_PAGE);
                } else {
                    path = matcher.group();
                }
            } else {
                path = PagePath.INDEX_PAGE;
            }
        }
        return path;
    }

    @Override
    public void destroy() {

    }
}
