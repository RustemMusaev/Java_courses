package ru.rustem.rambler.service;


import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.model.User;

public interface UserService {
    User findByLogin(String login) throws CustomException;

    boolean passwordIsCorrect(Integer userId, Integer passwordId, String pwd) throws CustomException;
}
