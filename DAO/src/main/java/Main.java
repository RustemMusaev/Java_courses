import factory.UserDaoFactory;
import dao.UsersDao;
import models.Car;
import models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao= UserDaoFactory.getInstance().getUsersDao();


        List<Car> Ivancarlist = new ArrayList<Car>();
        //Car Ivancar=new Car(26, "dodge", "black");
       // Ivancarlist.add(Ivancar);
       // Car Rustcar = new Car(36, "toyota", "green");
        User Ivan = new User(56, "Ivan", 102, Ivancarlist);
     //   User Rust = new User(45,"Rust",43);
     //   User Vasya = new User(185, "Vasya1", 34);
      //  User Alex = new User(185, "Vasya1", 34);
        //User rustlist=usersDao.find(3);
        //usersDao.delete(140);
        //System.out.println(rustlist);
        Ivan=usersDao.find(99);
        System.out.println(Ivan.getMycars().size());
        List<User> userList=usersDao.findAll();
        System.out.println(userList.size());
        UserDaoFactory.getInstance().closeConnection();

          }
}
