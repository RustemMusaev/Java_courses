package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.converter.DtoToUserConverter;
import ru.rustem.dao.UserDao;
import ru.rustem.dto.UserDto;
import ru.rustem.exception.UserNotFounException;
import ru.rustem.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private DtoToUserConverter converer = new DtoToUserConverter();

    public UserServiceImpl() {
    }

    @Override
    public boolean loginIsCorrect(String login) {
        return userDao.findByLogin(login) == null;
    }

    @Override
    public boolean save(UserDto model) {
        if (findByLogin(model.getLogin()) != null) return false;
        userDao.save(converer.convert(model));
        return true;
    }

    @Override
    public User findByLogin(String login) {
        User user = userDao.findByLogin(login);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFounException(login);
        }
    }
}
