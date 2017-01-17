package spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringServletContextListenerForJdbcProject implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context\\servlet-spring-jdbc.xml");
        servletContextEvent.getServletContext().setAttribute("jdbcSpringContext", applicationContext);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
