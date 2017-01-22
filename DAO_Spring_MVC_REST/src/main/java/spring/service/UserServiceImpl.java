package spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.dao.UsersDao;
import spring.models.Car;
import spring.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

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
