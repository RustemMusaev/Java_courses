package ru.itis.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

protected Class<?>[] getRootConfigClasses() {
    return new Class[]{WebSocketConfig.class};
}

protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebSocketConfig.class};
}

protected String[] getServletMappings() {
    return new String[]{"/"};
}
}
