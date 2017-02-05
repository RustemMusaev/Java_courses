package ru.itis.dao;

import ru.itis.model.Message;

import java.util.List;

public interface MessagesDao {
    List<Message> findAll();
    Message find(Integer id);
    Integer save(Message message);
    void delete(Integer id);
    void update(Message message);
    List<Message> findAllByChatId(Integer chatId);
    List<Message> findNewByChatId(Integer chatId,Integer userId);
}
