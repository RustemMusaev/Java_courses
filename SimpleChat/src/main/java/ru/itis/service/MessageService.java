package ru.itis.service;

import ru.itis.model.Message;

import java.util.List;

/**
 * Created by musaevrr on 28.01.2017.
 */
public interface MessageService {
    List<Message> findAll();
    Message find(Integer id);
    Integer save(Message message);
    void delete(Integer id);
    void update(Message message);
    List<Message> findAllByChatId(Integer chatId);
}
