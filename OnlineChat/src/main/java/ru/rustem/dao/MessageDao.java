package ru.rustem.dao;


import ru.rustem.model.Message;

import java.util.List;

/**
 * This interface contains two method for work with message
 */
public interface MessageDao {
    Integer save(Message message);
    List<Message> findAnyParam(String name, String cat, String min, String max);
    List<Message> findAll();
}
