package ru.itis.dao;

import org.springframework.context.annotation.Bean;
import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatUsersDao {
    List<ChatUser> findAll();
    ChatUser find(Integer id);
    Integer save(ChatUser chatUser);
    void delete(Integer id);
    void update(ChatUser chatUser);
    void saveUserToChat(Integer userId, Integer chatId);
    ChatUser findByLogin(String login);
    ChatUser findByToken(String token);
    boolean isExistToken(String token);
    boolean isExistLogin(String login);
    void saveToken(Integer user_id,String token);
    void deleteToken(String token);

}
