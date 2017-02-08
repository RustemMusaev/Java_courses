package ru.itis.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.itis.config.SecurityConfig;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("ru.itis.config")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityConfig.class,args);
    }

}
