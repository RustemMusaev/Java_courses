package ru.itis.dao;

import ru.itis.model.Message;

import java.util.List;

public interface MessagesDao {
    List<Message> findAll();
    Message find(Integer id);
    Integer save(Message chat);
    void delete(Integer id);
    void update(Message chat);
    List<Message> findAllByChatId(Integer chatId);
}
