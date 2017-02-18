package ru.itis.server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.server.models.User;
import ru.itis.server.repository.UsersRepository;

import javax.jws.WebService;
import java.util.List;

@Service
@WebService(endpointInterface = "ru.itis.server.services.UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User findOne(Integer id) {
        return usersRepository.findOne(id);
    }
    @Override
    public List<User> findAll() {
        return (List<User>) usersRepository.findAll();
    }
    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }
    @Override
    public void delete(Integer id) {
        usersRepository.delete(id);
    }
}
