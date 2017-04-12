package ru.rustem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * This class implements method UserDao interface
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    private UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method find all users
     *
     * @return set users of database
     */
    @Override
    public Set<User> findAll() {
        return new HashSet<User>(getSession().createQuery("FROM User").list());
    }

    /**
     * This method find user on hid id
     *
     * @param id - this user id for find
     * @return user
     */
    @Override
    public User find(Integer id) {
        return (User) getSession().createQuery("FROM User user where id = :id").setParameter("id", id).uniqueResult();
    }

    /**
     * This method save user on database
     *
     * @param user - user for need save
     * @return user ID, after completed opeation
     */
    @Override
    public Integer save(User user) {
        getSession().saveOrUpdate(user);
        return user.getId();
    }

    /**
     * This method use find(id)
     *
     * @param id - this user id, which use for find user
     * @return user token of database
     */
    @Override
    public String findTokenByUserId(Integer id) {
        return find(id).getToken();
    }

    /**
     * This method find User on his token
     *
     * @param token - use to find user of database
     * @return User, if this token is contain on database
     */
    @Override
    public User findUserByToken(String token) {
        return (User) getSession().createQuery("FROM User user where token = :token").setParameter("token", token).uniqueResult();
    }

    @Override
    public User findUserByLogin(String login) {
        return (User) getSession().createQuery("FROM User user where login = :login").setParameter("login", login).uniqueResult();
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) getSession().createQuery("FROM User user where email = :email").setParameter("email", email).uniqueResult();
    }

    @Override
    public Integer userIsCorrect(String login, String pwd) {
        User user = (User) getSession().createQuery("FROM User user where login = :login and passwordhash = :pwd").setParameter("login", login)
                .setParameter("pwd",pwd).uniqueResult();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
