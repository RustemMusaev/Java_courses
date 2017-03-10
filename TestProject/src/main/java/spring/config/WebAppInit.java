package spring.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spring.filter.LoginFilter;

import javax.servlet.*;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

@Override
protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{SpringConfig.class};
}

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new LoginFilter()};
    }
}
