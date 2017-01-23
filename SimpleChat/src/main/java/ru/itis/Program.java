package ru.itis;

import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatUsersDao;
import ru.itis.model.ChatUser;

import java.awt.*;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        reader.register(SpringConfig.class);
        context.refresh();
        ChatUsersDao chatUsersDao = (ChatUsersDao) context.getBean("chatUsersDao");
        System.out.println("hello");

        chatUsersDao.save(new ChatUser.Builder().login("Rustem").password_hash("dfgdfgd").builder());
    }
}
