package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.model.User;
import ru.rustem.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Override
    public boolean loginIsCorrect(String login) {
        return userDao.findByLogin(login) == null;
    }

    @Override
    public Integer save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
