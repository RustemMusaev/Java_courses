package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.dao.UserDao;
import ru.rustem.model.RegUser;
import ru.rustem.model.User;

import static ru.rustem.config.MyAuthenticatedWebSession.ENCODER;

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
    public Integer save(RegUser regUser) {
        User user = new User(regUser.getLogin(), ENCODER.encode(regUser.getPassword()), "USER");
        return userDao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
