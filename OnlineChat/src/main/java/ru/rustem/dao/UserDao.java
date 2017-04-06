package ru.rustem.dao;


import ru.rustem.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    Set<User> findAll();
    User find(Integer id);
    Integer save(User user);
    String findTokenByUserId(Integer id);
    User findUserByToken(String token);
}
