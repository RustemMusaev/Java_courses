package ru.itis.service;

import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatUserService {
    ChatUser find(Integer id);
    List<ChatUser> findAll();
    void save(ChatUser chatUser);
    void update(ChatUser chatUser);
    void delete(Integer id);
}