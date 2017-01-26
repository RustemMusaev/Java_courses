package spring.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
  }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userName = null;
        // получаем все куки
        Cookie[] cookies = request.getCookies();
        // есди куки непустые
        if(cookies !=null){
            // идем по всем кукам
            for(Cookie cookie : cookies){
                // если у нас печенька с атрибутом user совпала
                if(cookie.getName().equals("user")) {
                    // сохраняем значение в переменную userName
                    userName = cookie.getValue();
                }
            }
        }
        System.out.println(userName);
        System.out.println(request.getRequestURI());
        if (isLoginHtml(request)) {
            // смотрим, были ли куки
            if (userName == null) {
                //response.sendRedirect("login");
                // если не были - пусть идет на страницу
                //request.getRequestDispatcher("login").forward(request,response);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // в противном случае сразу бросаем в loginSuccess
                response.sendRedirect("users");
            }
        }
        // если не кулхацкер, то запрос идет от страницы index.jsp
        else if (isPostLogin(request)) {
            String currentUser = request.getParameter("user");
            if (currentUser.equals("Rustem")) {
                // создаем печеньку
                Cookie loginCookie = new Cookie("user", currentUser);
                // задали время жизни печеньки
                loginCookie.setMaxAge(30 * 60);
                // добавили печеньку в запрос
                response.addCookie(loginCookie);
                // делаем редирект на нужную страницу
                response.sendRedirect("users");
            }
        } else {
            if (userName != null) {
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

    private String getcookies(HttpServletRequest request) {
        String cookiName = null;
        // получаем все куки
        Cookie[] cookies = request.getCookies();
        // есди куки непустые
        if (cookies==null) {
            // идем по всем кукам
            for (Cookie cookie : cookies) {
                // если у нас печенька с атрибутом user совпала
                if (cookie.getName().equals("user")) {
                    // сохраняем значение в переменную userName
                    cookiName = cookie.getValue();
                }
            }
        }
        return cookiName;
    }
    private void addcookies(HttpServletResponse response,String currentUser) {
        // создаем печеньку
        Cookie loginCookie = new Cookie("user", currentUser);
        // задали время жизни печеньки
        loginCookie.setMaxAge(30*60);
        // добавили печеньку в запрос
        response.addCookie(loginCookie);
        }

    public void destroy() {

    }
}
