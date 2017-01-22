package spring.service;

import spring.models.Car;
import spring.models.User;

import java.util.List;

public interface UserService {

    public boolean IsRegistred(String name);
    public User find(int id);
    public List<User> findAll();
    public boolean save(User user);
    public boolean update(User user);
    public boolean delete(int id);
    public List<Car> getUserCars(int id);
}