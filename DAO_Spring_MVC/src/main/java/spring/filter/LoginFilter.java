package spring.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.models.User;
import spring.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Autowired
    private UserService userService;

    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
  }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String cookieName=getCookie(request);

        System.out.println(cookieName);
        System.out.println(request.getRequestURI());
        if (isLoginHtml(request)) {
            // смотрим, были ли куки
            if (cookieName == null) {
               // если не были - пусть идет на страницу
              filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // в противном случае сразу бросаем в loginSuccess
                response.sendRedirect("users");
            }
        }
        // если не кулхацкер, то запрос идет от страницы index.jsp
        else if (isPostLogin(request)) {
            String currentUser = request.getParameter("user");
            String currentPwd = request.getParameter("pwd");
            if (inspectionUser(currentUser)) {
                // создаем печеньку
                Cookie loginCookie = new Cookie("user", currentUser);
                // задали время жизни печеньки
                loginCookie.setMaxAge(30 * 60);
                // добавили печеньку в запрос
                response.addCookie(loginCookie);
                // делаем редирект на нужную страницу
                response.sendRedirect("users");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            if (cookieName != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                System.out.println("BAD REQUEST");
            }
        }
    }
    private boolean isLoginHtml(HttpServletRequest request) {
        return request.getRequestURI().equals("/");
    }

    private boolean isPostLogin(HttpServletRequest request) {
        return request.getRequestURI().equals("/login") && request.getMethod().equals("POST");
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
                if(cookie.getName().equals("user")) {
                    // сохраняем значение в переменную userName
                    cookieName = cookie.getValue();
                }
            }
        } return cookieName;
    }
    public boolean inspectionUser(String username){
        for (User user:userService.findAll()){
            if (user.getName().equals(username)){
                return true;
            }
        }
        return false;
    }
}
