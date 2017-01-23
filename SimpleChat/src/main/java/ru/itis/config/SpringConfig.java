package ru.itis.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.itis.dao.ChatUsersDao;
import ru.itis.dao.ChatUsersDaoImpl;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import javax.sql.DataSource;

@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "ru.itis")
public class SpringConfig {
    @Bean
    public ViewResolver viewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/", ".jsp");
    }
    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource());
        builder.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL82Dialect");
        builder.addAnnotatedClass(ChatUser.class);
        builder.addAnnotatedClass(Chat.class);
        return builder.buildSessionFactory();
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/DaoSpringMvc");
        driverManagerDataSource.setUsername("maven_allow");
        driverManagerDataSource.setPassword("Qaz!23$56");
        return driverManagerDataSource;
    }
    @Bean
    public ChatUsersDao chatUsersDao(){
        return new ChatUsersDaoImpl(sessionFactory());
    }
}
