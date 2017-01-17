package factory;

import dao.CarsDao;
import dao.UsersDao;
import service.CarService;
import service.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CarServiceFactory {
    private static CarServiceFactory instanse;
    private CarService carService;
    private  static String PROPERTIES_FILE_NAME =
            "C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\src\\main\\resources\\contex.properties";
    static {
        instanse=new CarServiceFactory();
    }
    public static CarServiceFactory getInstanse(){
        return instanse;
    }
    public CarService getCarService(){
        return carService;
    }

    private CarServiceFactory(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
            String carServiceClassName = properties.getProperty("carservice.class");
            Class<CarService> carServiceClass = (Class<CarService>) Class.forName(carServiceClassName);
            Constructor<?> constructor = carServiceClass.getConstructor(CarsDao.class);
            carService = (CarService) constructor.newInstance(CarDaoFactory.getInstance().getCarsDao());
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
