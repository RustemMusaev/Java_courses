package ru.rustem;

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
    @Autowired
    private UserService userService;
    /**
     *This method initializes the ServletContext for the doFilter ()
     * @param filterConfig - Input parameter for obtaining ServletContext
     * @throws ServletException - if don't get ServletContext
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
    }

    /**
     *This method processed all client request, which checks the request is allowed(The method isLoginHtml
     *  return true). If request is allow, this method makes redirect to controller, else redirect to
     *  error page(HttpStatus 404)
     * @param servletRequest - input configurathion client request
     * @param servletResponse - output configurathion client response
     * @param filterChain - use to redirect client request to controller
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String cookieName=getCookie(request);
        System.out.println(cookieName);
        System.out.println(request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * This method is used to determine the request for the controller.
     * @param request is HttpServletRequest that comes from the page
     * @return If the request URL is "/404", "/", "/users"
     * or starts with "/users/", "/getImage/" then the method returns true, otherwise false
     */
    private boolean isLoginHtml(HttpServletRequest request) {
        return request.getRequestURI().equals("/TestProject")
                || request.getRequestURI().equals("/TestProject/404")
                || request.getRequestURI().equals("/")
                || request.getRequestURI().equals("/TestProject/news")
                || request.getRequestURI().startsWith("/TestProject/news/")
                || request.getRequestURI().startsWith("/TestProject/getImage/");
    }
    public void destroy() {
    }
    public String getCookie(HttpServletRequest request){
        String cookieName=null;
        // получаем все куки
        Cookie[] cookies = request.getCookies();
        // есди куки непустые
        if(cookies !=null){
            // идем по всем кукам
            for(Cookie cookie : cookies){
                // если у нас печенька с атрибутом user совпала
                if(cookie.getName().equals("Auth-Token")) {
                    // сохраняем значение в переменную userName
                    cookieName = cookie.getValue();
                }
            }
        } return cookieName;
    }
}
