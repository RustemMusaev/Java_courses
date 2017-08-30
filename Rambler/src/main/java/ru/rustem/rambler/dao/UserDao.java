package ru.rustem.rambler.dao;

import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.models.User;


public interface UserDao {
    User findByLogin(String login) throws CustomException;
}
