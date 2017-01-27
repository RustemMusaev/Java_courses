package ru.itis.service;

import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatUserService {
    ChatUser find(Integer id);
    List<ChatUser> findAll();
    Integer save(ChatUser chatUser);
    void update(ChatUser chatUser);
    void delete(Integer id);
    void saveUserToChat(Integer userId, Integer chatId);
}