package ru.rustem.service;

import ru.rustem.dto.UserDto;
import ru.rustem.model.User;

public interface UserService {
    boolean loginIsCorrect(String login);

    boolean save(UserDto regUser);

    User findByLogin(String login);
}
