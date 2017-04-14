package ru.rustem;

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
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {
       filterChain.doFilter(servletRequest, servletResponse);
   }
    public void destroy() {
    }
}
