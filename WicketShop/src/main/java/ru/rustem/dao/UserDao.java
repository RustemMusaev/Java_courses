package ru.rustem.dao;

import ru.rustem.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    Integer save(User user);

    User findByLogin(String login);
}
