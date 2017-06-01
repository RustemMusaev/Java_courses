package ru.rustem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.rustem.config.SpringConfig;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("ru.rustem")
public class Application {
    public static void main(String[] args) {
        {
            SpringApplication.run(SpringConfig.class, args);
        }
    }
}
