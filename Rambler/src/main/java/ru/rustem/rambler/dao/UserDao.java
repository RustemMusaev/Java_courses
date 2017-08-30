package ru.rustem.rambler.dao;

import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.model.User;


public interface UserDao {
    User findByLogin(String login) throws CustomException;
}
