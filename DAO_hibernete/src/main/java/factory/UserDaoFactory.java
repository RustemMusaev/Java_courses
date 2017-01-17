package factory;

import dao.UsersDao;

import javax.sql.DataSource;
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
    private  static String PROPERTIES_FILE_NAME =
            "C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\src\\main\\resources\\contex.properties";
    static {
        instance=new UserDaoFactory();
    }
    public  static  UserDaoFactory getInstance() {
        return instance;
    }
    public static void closeConnection(){
        Connection connection = null;
    }
    public UsersDao getUsersDao() {
        return usersDao;
    }

    private UserDaoFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String userDaoClassName = properties.getProperty("users.dao.type");
        try {
                if (userDaoClassName.equals("jdbc")) {
                userDaoClassName = properties.getProperty("userdaojdbc");
                Constructor<?> constructor = Class.forName(userDaoClassName).getConstructor(DataSource.class);
                usersDao = (UsersDao)constructor.newInstance(DataSourceFactory.getInstanse().getDataSource());
                } else {
                String file = properties.getProperty("file");
                userDaoClassName = properties.getProperty("userdaofile");
                Class<UsersDao> usersDaoClass = (Class<UsersDao>) Class.forName(userDaoClassName);
                Constructor<UsersDao> usersDaoConstructor = usersDaoClass.getConstructor(String.class);
                usersDao = usersDaoConstructor.newInstance(file);
            }
        } catch ( ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
