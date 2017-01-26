package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.model.ChatUser;

import java.util.List;

@Repository
public class ChatUsersDaoImpl implements ChatUsersDao {

    private SessionFactory sessionFactory;
    @Autowired
    public ChatUsersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ChatUser> findAll() {
        return getSession().createQuery("FROM ChatUser").list();
    }

    @Override
    public ChatUser find(Integer id) {
        return getSession().createQuery("FROM ChatUser chatUser where id = :id", ChatUser.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(ChatUser chatUser) {
        getSession().saveOrUpdate(chatUser);
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(find(id));
    }

    @Override
    public void update(ChatUser chatUser) {
        getSession().update(chatUser);
    }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {

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
}
