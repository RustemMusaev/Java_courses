package spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.models.Car;
import spring.models.User;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDaoJdbcBasedImpl implements UsersDao {
    @Autowired
    private SessionFactory sessionFactory;

    private JdbcTemplate template;

    //language=SQL
    private static String SQL_SAVE_USER ="INSERT INTO group_user(name,age) VALUES (?, ?);";
    //language=SQL
    private static String SQL_DELETE_USER = "DELETE FROM group_user WHERE id=?";
    //language=SQL
    private static String SQL_UPDATE_USER = "UPDATE group_user SET name=?, age=? WHERE id=?";
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

   public User find(Integer id) {
        Session session = getSession();
       session.beginTransaction();
       User user =  session.createQuery("from User user where id = :id", User.class)
               .setParameter("id", id).getSingleResult();
       session.getTransaction().commit();
        return user;
    }

    public Integer save(User user) {
        return template.update(SQL_SAVE_USER,user.getName(),user.getAge());
    }
    public Integer update(User user) {
        return template.update(SQL_UPDATE_USER,user.getName(),user.getAge(),user.getId());
    }
    public Integer delete(Integer id) {
        return template.update(SQL_DELETE_USER,id);
    }
    public List<Car> getUserCars(Integer id) {
        List<Car> carList =new ArrayList<Car>();
        User user=find(id);
        carList=user.getMycars();
        return carList;
    }
}