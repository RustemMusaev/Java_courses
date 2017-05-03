package ru.rustem.dao;

import org.apache.log4j.Logger;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.model.User;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @SpringBean
    private SessionFactory sessionFactory;
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);


    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        return getSession().createQuery("FROM User").list();
    }

    @Override
    public Integer save(User user) {
        getSession().saveOrUpdate(user);
        Integer id = user.getId();
        if (id != null && log.isInfoEnabled()) {
            log.info("Save User id = " + id);
        }
        return id;
    }

    @Override
    public User findByLogin(String login) {
        User user = (User) getSession().createQuery("FROM User user where login = :login")
                .setParameter("login", login).uniqueResult();
        if (user != null) return user;
        return null;
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
