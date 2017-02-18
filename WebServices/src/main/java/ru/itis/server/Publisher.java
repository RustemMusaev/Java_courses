package ru.itis.server;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.SpringConfig;
import ru.itis.server.services.AutoService;
import ru.itis.server.services.AutoServiceImpl;
import ru.itis.server.services.UserService;
import ru.itis.server.services.UserServiceImpl;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean(UserServiceImpl.class);
        AutoService autoService = context.getBean(AutoServiceImpl.class);
        Endpoint.publish("http://localhost:1234/ws/user", userService);
        Endpoint.publish("http://localhost:1234/ws/auto", autoService);
    }
}
