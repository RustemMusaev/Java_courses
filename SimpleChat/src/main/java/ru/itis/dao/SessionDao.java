package ru.itis.dao;

import ru.itis.model.ChatUser;
import ru.itis.model.Session;

import java.util.List;

public interface SessionDao {
    List<Session> findAll();
    Session find(Integer id);
    Integer save(Session chat);
    void delete(Integer id);
    void update(Session chat);
    boolean isExistsToken (String token);
    ChatUser findUserByToken (String token);
    void updateToken(Integer user_id,String token);
}
