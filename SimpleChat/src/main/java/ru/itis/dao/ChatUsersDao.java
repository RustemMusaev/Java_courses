package ru.itis.dao;

import org.springframework.context.annotation.Bean;
import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatUsersDao {
    List<ChatUser> findAll();
    ChatUser find(Integer id);
    void save(ChatUser chatUser);
    void delete(Integer id);
    void update(ChatUser chatUser);
    void saveUserToChat(Integer userId, Integer chatId);
}
