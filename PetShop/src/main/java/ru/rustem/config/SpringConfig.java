package ru.rustem.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.rustem")
public class SpringConfig extends WebMvcConfigurerAdapter {
    private final static int PERIOD_ONE_MONTH = 60 * 60 * 24 * 30;
    private final static String RESOURCE = "config.xml";

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public DataSource dataSource() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Properties file don't input");
        }
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("db.driver"));
        driverManagerDataSource.setUrl(properties.getProperty("db.url"));
        driverManagerDataSource.setUsername(properties.getProperty("db.user"));
        driverManagerDataSource.setPassword(properties.getProperty("db.pwd"));
        return driverManagerDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(new ClassPathResource(RESOURCE));
        sqlSessionFactory.setDataSource();
        sqlSessionFactory.setFailFast(true);
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("WEB-INF/js/").setCachePeriod(PERIOD_ONE_MONTH);
        registry.addResourceHandler("/css/**").addResourceLocations("WEB-INF/css").setCachePeriod(PERIOD_ONE_MONTH);
    }

}
