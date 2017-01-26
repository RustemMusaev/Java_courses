package ru.itis;

import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatUsersDao;
import ru.itis.model.ChatUser;
import ru.itis.service.GenerateHashMd5;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        reader.register(SpringConfig.class);
        context.refresh();
        ChatUsersDao chatUsersDao = (ChatUsersDao) context.getBean("chatUsersDao");
        //chatUsersDao.delete(1);
        ChatUser temp=new ChatUser.Builder().id(3).login("Masha").password_hash("123").builder();
        chatUsersDao.update(temp);
        List<ChatUser> chatUserList=chatUsersDao.findAll();
        for (ChatUser chatUser: chatUserList){
            System.out.println(chatUser.getId()+"\t"+chatUser.getLogin() +"\t"+ chatUser.getPassword_hash());
        }
    }
}
