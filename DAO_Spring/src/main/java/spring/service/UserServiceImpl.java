package spring.service;


import spring.dao.UsersDao;
import spring.models.Car;
import spring.models.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UsersDao usersDao;

    public UserServiceImpl(UsersDao usersDao) {
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
    public boolean update(User user){return usersDao.update(user);
    };
    public boolean delete(int id){
        return usersDao.delete(id);
    };
    public List<Car> getUserCars(int id){
        return usersDao.getUserCars(id);
    };

}
