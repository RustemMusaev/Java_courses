package spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.UsersDao;
import spring.models.Car;
import spring.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

    public User find(Integer id){
        return usersDao.find(id);
    }
    public List<User> findAll(){
        return  usersDao.findAll();
    }
    public Integer save(User user){return usersDao.save(user);}
    public Integer update(User user){return usersDao.update(user);}
    public Integer delete(Integer id){
        return usersDao.delete(id);
    }
    public List<Car> getCar(Integer id){
        return usersDao.getUserCars(id);
    }
}
