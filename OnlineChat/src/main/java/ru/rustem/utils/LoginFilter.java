package ru.rustem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.rustem.service.UserService;

import javax.servlet.*;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is the input point of client requests to the server.
 * Here, requests are processed and redirected to the controller
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String cookieName = getCookie(request);
        System.out.println(cookieName);
        System.out.println(request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

    public String getCookie(HttpServletRequest request) {
        String cookieName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth-Token")) {
                    cookieName = cookie.getValue();
                }
            }
        }
        return cookieName;
    }
}
