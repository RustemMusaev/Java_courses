package spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.models.Car;
import spring.models.User;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDaoJdbcBasedImpl implements UsersDao {
    @Autowired
    private SessionFactory sessionFactory;

    private JdbcTemplate template;

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
    @Autowired
    public UsersDaoJdbcBasedImpl(DataSource dataSource) {
        this.template=new JdbcTemplate(dataSource);
    }

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public List<User> findAll() {
       List<User> userList;
       Session session=getSession();
       session.beginTransaction();
       userList=session.createQuery("from User",User.class).list();
       session.getTransaction().commit();
       return userList;
    }

   public User find(int id) {
        Session session = getSession();
        session.beginTransaction();
        User user =  session.createQuery("from User user where id = :id", User.class)
                .setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return user;
    }

    public boolean save(User user) {
        if (template.update(SQL_SAVE_USER,user.getId(),user.getName(),user.getAge())==1) {
            return true;
        } else return false;
    }

    public boolean update(User user) {
        if (template.update(SQL_UPDATE_USER,user.getName(),user.getAge(),user.getId())==1) {
            return true;
        } else return false;
    }

    public boolean delete(int id) {
        if (template.update(SQL_DELETE_USER,id)==1) {
            if (find(id).getMycars()!=null) {
                template.update(SQL_DELETE_USER_CARS,id);
                }
              return true;
        } else return false;
    }

    public List<Car> getUserCars(int id) {
        List<Car> carList =new ArrayList<Car>();
        User user=find(id);
        carList=user.getMycars();
        return carList;
    }
}