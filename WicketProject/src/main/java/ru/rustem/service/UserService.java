package ru.rustem.service;

import ru.rustem.model.RegUser;
import ru.rustem.model.User;

public interface UserService {
    boolean loginIsCorrect(String login);

    Integer save(RegUser regUser);

    User findByLogin(String login);
}
