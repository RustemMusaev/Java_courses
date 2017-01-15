package dao;

import models.Car;
import models.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

public interface UsersDao {
    User find(int id);
    List<User> findAll();
    boolean save(User user);
    boolean update(User user);
    boolean delete(int id);
    List<Car> getUserCars(int id);
}