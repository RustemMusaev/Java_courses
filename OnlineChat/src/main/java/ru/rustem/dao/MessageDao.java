package ru.rustem.dao;


import ru.rustem.model.Message;

import java.util.List;

public interface MessageDao {
    Integer save(Message message);
    List<Message> findAll();
}
