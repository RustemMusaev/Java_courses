package ru.rustem.rambler.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.models.User;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByLogin(String login) throws CustomException {
        try {
            return getSession().createQuery("FROM User user where user.login = :login", User.class)
                    .setParameter("login", login).getSingleResult();
        } catch (NoResultException ex) {
            throw new CustomException("does n't found user login = " + login);
        }
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
