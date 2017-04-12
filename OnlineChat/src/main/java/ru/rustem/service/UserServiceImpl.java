package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.dao.UserDao;
import ru.rustem.dto.UserLogin;
import ru.rustem.model.User;

import java.util.Set;
import java.util.UUID;

import static ru.rustem.converter.UserRegistrationToUserConverter.passwordEncoder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

     UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

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

    /**
     * This method checks login if such in the database
     *
     * @param login for check
     * @return true, if this login find on database. else return false
     */
    @Override
    public boolean loginIsCorrect(String login) {
        if (userDao.findUserByLogin(login) != null) {
            return true;
        }
        return false;
    }
    /**
     * This method used for authorization user. At first compare login, if this login is used, at second match password
     *
     * @param userLogin - user for authorization(contains login and password)
     * @return user id, if this login find on database. else return null
     */
    @Override
    public Integer loginUser(UserLogin userLogin) {
        User user = userDao.findUserByLogin(userLogin.getUsername());
            if (user != null && passwordEncoder.matches(userLogin.getPwd(), user.getPasswordHash())) {
                    UUID tokenUUID = UUID.randomUUID();
                    String token = tokenUUID.toString();
                    user.setToken(token);
                    userDao.save(user);
                    return user.getId();
                } else {
                    return null;
                }
    }

    @Override
    public String findTokenByUserId(Integer id) {
        return userDao.findTokenByUserId(id);
    }

    @Override
    public User findUserByToken(String token) {
        return userDao.findUserByToken(token);
    }

    @Override
    public boolean emailIsCorrect(String email) {
        if (userDao.findUserByEmail(email) != null) {
            return true;
        }
        return false;
    }
}

