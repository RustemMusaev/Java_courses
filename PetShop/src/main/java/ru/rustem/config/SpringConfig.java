package ru.rustem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.rustem")
public class SpringConfig extends WebMvcConfigurerAdapter {
    int PERIOD_ONE_MONTH = 60 * 60 * 24 * 30;

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(PERIOD_ONE_MONTH);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(PERIOD_ONE_MONTH);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(PERIOD_ONE_MONTH);
    }

}
