import factory.CarServiceFactory;
import factory.UserDaoFactory;
import dao.UsersDao;
import factory.UserServiceFactory;
import models.Car;
import models.User;
import service.CarService;
import service.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService= UserServiceFactory.getInstanse().getUserService();
        CarService carService= CarServiceFactory.getInstanse().getCarService();
        //userService.IsRegistred("dd");


        List<Car> Ivancarlist = new ArrayList<Car>();
        //Car Ivancar=new Car(26, "dodge", "black");
       // Ivancarlist.add(Ivancar);
        User Rust = new User(45,"Rust",43);
        Car Rustcar = new Car(1233, "toyota", "green",Rust);
        User Ivan = new User(56, "Ivan", 102, Ivancarlist);
       // User Rust = new User(45,"Rust",43);
     //   User Vasya = new User(185, "Vasya1", 34);
      //  User Alex = new User(185, "Vasya1", 34);
        //User rustlist=usersDao.find(3);
        //usersDao.delete(140);
        //System.out.println(rustlist);
        Ivan=userService.find(99);
        carService.save(Rustcar);
        //System.out.println(Ivan.getMycars().size());
        //List<User> userList=userService.findAll();
       // System.out.println(userList.size());
        //UserDaoFactory.getInstance().closeConnection();
          }
}
