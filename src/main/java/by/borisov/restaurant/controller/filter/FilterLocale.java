//
//package by.borisov.restaurant.controller.filter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static by.borisov.restaurant.controller.command.FormParameterName.FORM_PARAM_LOCALE;
//import static by.borisov.restaurant.controller.command.FormParameterName.LANGUAGE_RU;
//
//
///**
// * Class checks and sets required locale
// */
//
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//
//@WebFilter(urlPatterns = {"/*"})
//public class FilterLocale implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//
///**
//     * Method is called every time when filter accessed
//     *
//     * @param servletRequest  current request
//     * @param servletResponse current response
//     * @param filterChain     list of all filters
//     * @throws IOException      if happens IOException
//     * @throws ServletException if happens ServletException
//     */
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpSession session = request.getSession();
//        if (session.getAttribute(FORM_PARAM_LOCALE) == null) {
//            session.setAttribute(FORM_PARAM_LOCALE, LANGUAGE_RU);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
