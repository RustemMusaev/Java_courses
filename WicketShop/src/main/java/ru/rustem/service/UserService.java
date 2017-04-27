package ru.rustem.service;

import ru.rustem.model.User;

public interface UserService {
    boolean loginIsCorrect(String login);

    Integer save(User user);

    User findByLogin(String login);
}
