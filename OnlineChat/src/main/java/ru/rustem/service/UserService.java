package ru.rustem.service;

import ru.rustem.dto.UserLogin;
import ru.rustem.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    Set<User> findAll();
    User find(Integer id);
    Integer save(User user);
    boolean loginIsCorrect(String login);
    Integer loginUser(UserLogin userLogin);
    String findTokenByUserId(Integer id);
    User findUserByToken(String token);


}
