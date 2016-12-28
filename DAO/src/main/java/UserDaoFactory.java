import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDaoFactory instance;

    private UsersDao usersDao;
    static {
        instance=new UserDaoFactory();
    }
    public  static  UserDaoFactory getInstance() {
        return instance;
    }
    public UsersDao getUsersDao() {
        return usersDao;
    }
    public void closeConnection(){
     //   connection=null;
    }
    private UserDaoFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\src\\main\\resources\\contex.properties"));
            String userDaoClassName = properties.getProperty("users.dao.type");
            if (userDaoClassName.equals("jdbc")) {
                System.out.println("1");
                String url = properties.getProperty("jdbc.url");
                String name = properties.getProperty("jdbc.name");
                String password = properties.getProperty("jdbc.password");
                Class.forName("org.postgresql.Driver");
                Connection connection = null;
                connection = DriverManager.getConnection(url, name, password);
                String UserDaoClassName = properties.getProperty("userdaojdbc");
                Class<UsersDao> usersDaoClass = (Class<UsersDao>) Class.forName(UserDaoClassName);
                Constructor<UsersDao> usersDaoConstructor = usersDaoClass.getConstructor(Connection.class);
                usersDao = usersDaoConstructor.newInstance(connection);
        } else {
                System.out.println("2");
                String file = properties.getProperty("file");
                String UserDaoClassName = properties.getProperty("userdaofile");
                Class<UsersDao> usersDaoClass = (Class<UsersDao>) Class.forName(UserDaoClassName);
                Constructor<UsersDao> usersDaoConstructor = usersDaoClass.getConstructor(String.class);
                usersDao = usersDaoConstructor.newInstance(file);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
