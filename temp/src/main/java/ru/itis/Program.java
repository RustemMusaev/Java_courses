package ru.itis;

import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        reader.register(SpringConfig.class);
        context.refresh();
        ChatUsersDao chatUsersDao = (ChatUsersDao) context.getBean("chatUsersDao");

        //chatUsersDao.save(new ru.itis.ChatUser.Builder().login("Rustem").password_hash("dfgdfgd").builder());
        List<ChatUser> chatUserList=chatUsersDao.findAll();
        for (ChatUser chatUser: chatUserList){
            System.out.println(chatUser.getLogin());
        }
    }
}
