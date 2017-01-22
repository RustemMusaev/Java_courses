package spring.dao;

import spring.models.Car;
import spring.models.User;

import java.util.List;

public interface UsersDao {
    User find(int id);

    List<User> findAll();

    boolean save(User user);

    boolean update(User user);

    boolean delete(int id);

    List<Car> getUserCars(int id);
}