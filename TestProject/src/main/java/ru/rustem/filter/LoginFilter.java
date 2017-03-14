package ru.rustem.filter;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is the input point of client requests to the server.
 * Here, requests are processed and redirected to the controller
 */
public class LoginFilter implements Filter {
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
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      if (isLoginHtml(request)) {
          filterChain.doFilter(servletRequest, servletResponse);
      } else {
          System.out.println("BAD REQUEST");
          response.sendRedirect("/404");
      }
  }

    /**
     * This method is used to determine the request for the controller.
     * @param request is HttpServletRequest that comes from the page
     * @return If the request URL is "/404", "/", "/users"
     * or starts with "/users/", "/getImage/" then the method returns true, otherwise false
     */
   private boolean isLoginHtml(HttpServletRequest request) {
      return request.getRequestURI().equals("/")
              || request.getRequestURI().equals("/404")
              || request.getRequestURI().equals("/news")
              || request.getRequestURI().startsWith("/news/")
              || request.getRequestURI().startsWith("/getImage/");
    }
    public void destroy() {
    }
}
