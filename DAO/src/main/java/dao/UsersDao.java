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
    public void printmycarslist(User users);
    public List<Car> getUserCars(int user_id);
}