package spring.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@EnableWebMvc
@Configuration
@ComponentScan("spring")
public class SpringConfig{
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resourceView= new InternalResourceViewResolver();
        resourceView.setPrefix("/");
        resourceView.setSuffix(".jsp");
        return resourceView;
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.springframework.jdbc.datasource.DriverManagerDataSource");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/MavenDatabase");
        driverManagerDataSource.setUsername("maven_allow");
        driverManagerDataSource.setPassword("Qaz!23$56");
        return driverManagerDataSource;
    }
    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource());
        builder.addResource("hibernate/User.hbm.xml");
        builder.addResource("hibernate/Car.hbm.xml");
        builder.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL82Dialect");
        return builder.buildSessionFactory();
    }
}
