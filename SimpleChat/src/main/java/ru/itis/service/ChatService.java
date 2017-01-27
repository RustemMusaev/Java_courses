package ru.itis.service;

import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatService {
    List<Chat> findAll();
    Chat find(Integer id);
    Integer save(Chat chat);
    void delete(Integer id);
    void update(Chat chat);
}