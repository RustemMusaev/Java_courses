package spring.service;

import spring.models.Car;
import spring.models.User;

import java.util.List;

public interface UserService {
    User find(Integer id);
    List<User> findAll();
    Integer save(User user);
    Integer update(User user);
    Integer delete(Integer id);
    List<Car> getCar(Integer id);
}