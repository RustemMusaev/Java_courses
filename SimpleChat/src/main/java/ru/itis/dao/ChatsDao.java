package ru.itis.dao;

import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatsDao {
    List<Chat> findAll();
    Chat find(Integer id);
    Integer save(Chat chat);
    void delete(Integer id);
    void update(Chat chat);
}
