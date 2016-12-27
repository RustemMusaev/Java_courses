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
       String userFileName="C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\users.txt";
       String carsFileName="C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\cars.txt";
       String url="jdbc:postgresql://localhost:5432/MavenDatabase";
       String name="maven_allow";
       String password="Qaz!23$56";
       Connection connection=null;

       try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            CarsDaoSqlBasedImpl carsDaoSqlBased = new CarsDaoSqlBasedImpl(connection);
            Cars car = carsDaoSqlBased.find(11);
            Cars Volga = new Cars(24, "volga", "red", 102);

           Cars Ivancar = new Cars(26, "dodge", "black");
           Cars Rustcar = new Cars(36, "toyota", "green");
           User Ivan = new User(45, "Ivan", 102, Ivancar);
            User Rust=new User(18,"Rust",30,Rustcar);
            User Vasya = new User(85, "Vasya1", 34);
            UsersDaoSqlBasedImpl usersDaoSqlBased=new UsersDaoSqlBasedImpl(connection);
            usersDaoSqlBased.save(Rust);
            // List<User> userList= usersDaoSqlBased.findAll();
           // System.out.println(usersDaoSqlBased.update(Vasya));
            //usersDaoSqlBased.delete(85);
            connection = null;



        } catch (ClassNotFoundException e) {
               e.printStackTrace();
             } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
