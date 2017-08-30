package ru.rustem.rambler.dao;

import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.model.Password;

public interface PasswordDao {
    Password findById(Integer id);
    String findPasswordUseFile(Integer userId) throws CustomException;

}
