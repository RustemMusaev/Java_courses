package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rustem.model.User;
import ru.rustem.model.UserLogin;
import ru.rustem.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static Integer COUNT = 10;

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Page<User> getPageNews(Integer pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, COUNT, Sort.Direction.DESC, "id");
        return userRepository.findAll(request);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public boolean update(User user, Integer id) {
        User currentUser = findById(id);
        if (!user.getName().equals("")) {
            currentUser.setName(user.getName());
        }
        if (!user.getSurname().equals("")) {
            currentUser.setSurname(user.getSurname());
        }
        if (!user.getBirthday().equals("")) {
            currentUser.setBirthday(user.getBirthday());
        }
        if (!user.getLogin().equals("")) {
            currentUser.setLogin(user.getLogin());
        }
        if (!user.getPassword().equals("")) {
            currentUser.setPassword(user.getPassword());
        }
        if (!user.getAboutMe().equals("")) {
            currentUser.setAboutMe(user.getAboutMe());
        }
        if (!user.getAddress().equals("")) {
            currentUser.setAddress(user.getAddress());
        }
        userRepository.save(currentUser);
        return false;
    }

    @Override
    public User findUserByToken(String token) {
        User user = userRepository.findUserByToken(token);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        User user = userRepository.findUserByLogin(login);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User userIsCorrect(UserLogin userLogin) {
        User user = findUserByLogin(userLogin.getLogin());
        if (user != null && passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            UUID tokenUUID = UUID.randomUUID();
            String token = tokenUUID.toString();
            user.setToken(token);
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean closeSession(String token) {
        User user = findUserByToken(token);
        if (user != null) {
            user.setToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
