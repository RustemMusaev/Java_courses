package factory;

import dao.UsersDao;
import service.UserService;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class UserServiceFactory {
    private static UserServiceFactory instanse;
    private UserService userService;
    private  static String PROPERTIES_FILE_NAME =
            "C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\src\\main\\resources\\contex.properties";
    static {
        instanse=new UserServiceFactory();
    }
    public static UserServiceFactory getInstanse(){
        return instanse;
    }
    public UserService getUserService(){
        return userService;
    }

    private UserServiceFactory(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
            String userServiceClassName = properties.getProperty("userservice.class");
            Class<UserService> userServiceClass = (Class<UserService>) Class.forName(userServiceClassName);
            Constructor<UserService> constructor = userServiceClass.getConstructor(UsersDao.class);
            userService = (UserService) constructor.newInstance(UserDaoFactory.getInstance().getUsersDao());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
