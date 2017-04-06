package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.dao.UserDao;
import ru.rustem.dto.UserLogin;
import ru.rustem.model.User;
import ru.rustem.utils.TokenGenerator;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenGenerator generator;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Set<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User find(Integer id) {
        return userDao.find(id);
    }

    @Override
    public Integer save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean loginIsCorrect(String login) {
        for (User user : userDao.findAll()) {
            if (user.getLogin().equals(login)) return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Integer loginUser(UserLogin userLogin) {
        for (User user : userDao.findAll()) {
            if (user.getLogin().equals(userLogin.getUsername())) {
                if (encoder.matches(userLogin.getPwd(), user.getPasswordHash())) {
                    String token = generator.generateToken();
                    user.setToken(token);
                    userDao.save(user);
                    return user.getId();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public String findTokenByUserId(Integer id) {
        return userDao.findTokenByUserId(id);
    }

    @Override
    public User findUserByToken(String token) {
        return userDao.findUserByToken(token);
    }
}

