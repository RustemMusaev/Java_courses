package ru.rustem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;
    @Autowired
    private UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    public UserDaoImpl() {
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<User>(getSession().createQuery("FROM User").list());
    }
    @Override
    public User find(Integer id) {
        return (User) getSession().createQuery("FROM User user where id = :id").setParameter("id", id).uniqueResult();
    }

    @Override
    public Integer save(User user) {
         getSession().saveOrUpdate(user);
         return user.getId();
    }

    @Override
    public String findTokenByUserId(Integer id) {
        return find(id).getToken();
    }

    @Override
    public User findUserByToken(String token) {
        return (User) getSession().createQuery("FROM User user where token = :token").setParameter("token", token).uniqueResult();
    }
    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
      //  session = sessionFactory.openSession();
        return session;
    }
}
