package service;

import dao.UsersDao;
import models.Car;
import models.User;

import java.util.List;
import java.util.Map;

public class UserService {

    private UsersDao usersDao;

    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }
    public boolean IsRegistred(String name) {
        return true;
    }
    public User find(int id){
       return usersDao.find(id);
    };
    public List<User> findAll(){
        return  usersDao.findAll();
    };
    public boolean save(User user){
        return usersDao.save(user);
    };
    public boolean update(User user){
        return usersDao.update(user);
    };
    public boolean delete(int id){
        return usersDao.delete(id);
    };
    public List<Car> getUserCars(int id){
        return usersDao.getUserCars(id);
    };
}