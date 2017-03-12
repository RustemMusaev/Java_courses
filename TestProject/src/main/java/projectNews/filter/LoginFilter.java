package projectNews.filter;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
  public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
  }
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      System.out.println(request.getRequestURI());
      if (isLoginHtml(request)) {
          filterChain.doFilter(servletRequest, servletResponse);
      } else {
          System.out.println("BAD REQUEST");
          response.sendRedirect("/404");
      }
  }
   private boolean isLoginHtml(HttpServletRequest request) {
      return request.getRequestURI().equals("/")
              || request.getRequestURI().equals("/404")
              || request.getRequestURI().equals("/users")
              || request.getRequestURI().startsWith("/users/")
              || request.getRequestURI().startsWith("/getImage/");
    }
    public void destroy() {
    }
}
