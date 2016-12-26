import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {

    public static void main(String[] args) {
       String userFileName="C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\users.txt";
       String carsFileName="C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\cars.txt";


      CarsDaoSqlBasedImpl carsDao=new CarsDaoSqlBasedImpl();
      Cars Volga=new Cars(24,"volga","red",102);
      User Ivan=new User(24,"Ivan",102);
      User Vasya=new User(85,"Vasya1",34);


        //carsDao.update(Volga);
        UsersDaoSqlBasedImpl usersDaoSqlBased=new UsersDaoSqlBasedImpl();
       // List<User> userList= usersDaoSqlBased.findAll();
       //System.out.println(usersDaoSqlBased.update(Vasya));
        //usersDaoSqlBased.printmycarslist(Vasya);

    }
}
