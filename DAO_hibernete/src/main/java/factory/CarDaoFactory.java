package factory;

import dao.CarsDao;
import dao.UsersDao;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

public class CarDaoFactory {

    private static CarDaoFactory instance;
    private CarsDao carsDao;
    private  static String PROPERTIES_FILE_NAME =
            "C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\src\\main\\resources\\contex.properties";
    static {
        instance=new CarDaoFactory();
        }
    public  static  CarDaoFactory getInstance() {
        return instance;
        }
    public static void closeConnection(){
        Connection connection = null;
        }
    public CarsDao getCarsDao() {
        return carsDao;
        }

    private CarDaoFactory() {
        Properties properties = new Properties();
        try {
        properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
        String carDaoClassName = properties.getProperty("cardaofile");
        Constructor<?> constructor = Class.forName(carDaoClassName).getConstructor(DataSource.class);
        carsDao = (CarsDao) constructor.newInstance(DataSourceFactory.getInstanse().getDataSource());
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
