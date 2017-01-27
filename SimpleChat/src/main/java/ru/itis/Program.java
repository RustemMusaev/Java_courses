package ru.itis;

import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatUsersDao;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;
import ru.itis.service.ChatService;
import ru.itis.service.ChatUserService;
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

        ChatUserService chatUserService = context.getBean("chatUserService", ChatUserService.class);
        ChatService chatService=context.getBean("chatService",ChatService.class);
       // chatUsersDao.delete(1);
        //ChatUser temp=new ChatUser.Builder().id(3).login("Masha").password_hash("321").builder();
        //chatUserService.update(temp);
       // Chat chat=new Chat.Builder().name("Mychat").build();
        //chatService.save(chat);
       // ChatUser rustem=chatUserService.find(1);
        chatUserService.saveUserToChat(2,2);
        for (ChatUser chatUser: chatUserService.findAll()){
            System.out.println(chatUser.getId()+"\t"+chatUser.getLogin() +"\t"+ chatUser.getPassword_hash()+"\t"
                    +chatUser.getChatSet());
        }
    }
}
