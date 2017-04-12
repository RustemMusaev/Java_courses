package ru.rustem.dao;


import ru.rustem.model.User;

import java.util.Set;

/**
 * This interface contains five method for work with user
 */
public interface UserDao {
    Set<User> findAll();

    User find(Integer id);

    Integer save(User user);

    String findTokenByUserId(Integer id);

    User findUserByToken(String token);

    User findUserByLogin(String login);

    User findUserByEmail(String email);

    Integer userIsCorrect(String login, String password);
}
