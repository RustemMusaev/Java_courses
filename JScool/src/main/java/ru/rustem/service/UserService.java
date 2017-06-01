package ru.rustem.service;


import org.springframework.data.domain.Page;
import ru.rustem.model.User;
import ru.rustem.model.UserLogin;

import java.util.List;

/**
 * This interface contains two method for work
 */
public interface UserService {
    Page<User> getPageNews(Integer pageNumber);

    User save(User user);

    List<User> findAll();

    void delete(Integer id);

    User findById(Integer id);

    boolean update(User user, Integer id);

    User findUserByToken(String token);

    User findUserByLogin(String login);

    User userIsCorrect(UserLogin userLogin);

    boolean closeSession(String token);

}
