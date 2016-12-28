import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao=UserDaoFactory.getInstance().getUsersDao();

               Cars Ivancar = new Cars(26, "dodge", "black");
               Cars Rustcar = new Cars(36, "toyota", "green");
               User Ivan = new User(45, "Ivan", 102, Ivancar);
               User Rust=new User(43,"Rust",43);
               User Vasya = new User(85, "Vasya1", 34);
               User rustlist=usersDao.find(3);
        System.out.println(rustlist);
          }
}
