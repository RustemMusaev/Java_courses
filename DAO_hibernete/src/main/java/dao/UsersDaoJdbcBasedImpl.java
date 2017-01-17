package dao;

import hibernate.HibernateConnector;
import models.Car;
import models.User;
import org.hibernate.Session;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDaoJdbcBasedImpl implements UsersDao {

    private Session session;
    private JdbcTemplate template;
    private Map<Integer,User> usersMap;

    //language=SQL
    private static String SQL_FIND_CAR_BY_USER_ID="SELECT * FROM car WHERE car_user_id=?";
    //language=SQL
    private static String SQL_FIND_USER_BY_ID="SELECT * FROM group_user WHERE user_id=?";
    //language=SQL
    private static String SQL_FIND_ALL_USER="SELECT * FROM group_user";
    //language=SQL
    private static String SQL_FIND_ALL_CAR="SELECT * FROM car";
    //language=SQL
    private static String SQL_SAVE_USER ="INSERT INTO group_user VALUES (?, ?, ?);";
    //language=SQL
    private static String SQL_DELETE_USER = "DELETE FROM group_user WHERE user_id=?";
    //language=SQL
    private static String SQL_DELETE_USER_CARS = "DELETE FROM car WHERE car_user_id=?";
    //language=SQL
    private static String SQL_UPDATE_USER = "UPDATE group_user SET user_name=?, user_age=? WHERE user_id=?";

    public UsersDaoJdbcBasedImpl(DataSource dataSource) {
        this.template=new JdbcTemplate(dataSource);
        this.usersMap= new HashMap<Integer, User>();
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(resultSet.getInt("user_id"), resultSet.getString("user_name"),
                    resultSet.getInt("user_age"), new ArrayList<Car>());
            usersMap.put(user.getId(), user);
            return user;
        }
    };
    private RowMapper<Car> carRowMapper = new RowMapper<Car>() {
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car(resultSet.getInt("car_id"),resultSet.getString("car_model"), resultSet.getString("car_color"));
            int id=resultSet.getInt("car_user_id");
            User user = usersMap.get(id);
            user.getMycars().add(car);
            car.setUser(user);
            return car;
        }
    };
    @Override
    public List<User> findAll() {
       List<User> userList;
       this.session=HibernateConnector.getConnector().getSession();
       session.beginTransaction();
       userList=session.createQuery("from User",User.class).list();
       session.getTransaction().commit();
       return userList;
    }
    @Override
    public User find(int id) {

        this.session = HibernateConnector.getConnector().getSession();
        session.beginTransaction();
        User user =  session.createQuery("from User user where id = :id", User.class)
                .setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return user;
    }
    @Override
    public boolean save(User user) {
        if (template.update(SQL_SAVE_USER,user.getId(),user.getName(),user.getAge())==1) {
            System.out.println("save user");
            return true;
        } else return false;
    }
    @Override
    public boolean update(User user) {
        if (template.update(SQL_UPDATE_USER,user.getName(),user.getAge(),user.getId())==1) {
            System.out.println("update user");
            return true;
        } else return false;
    }
    @Override
    public boolean delete(int id) {
        if (template.update(SQL_DELETE_USER,id)==1) {
            if (usersMap.get(id).getMycars()!=null) {
                template.update(SQL_DELETE_USER_CARS,id);
                }
            System.out.println("delete user and his cars");
            return true;
        } else return false;
    }
    @Override
    public List<Car> getUserCars(int id) {
        List<Car> carList =new ArrayList<Car>();
        User user=usersMap.get(id);
        carList=user.getMycars();
        System.out.println("find car");
        return carList;
    }
}
