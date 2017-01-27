package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.model.Chat;

import java.util.List;
@Repository("chatsDao")
public class ChatsDaoImpl implements ChatsDao {

    private SessionFactory sessionFactory;
    @Autowired
    private ChatsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }
    @Override
    public List<Chat> findAll() {
        return getSession().createQuery("FROM Chat ").list();
    }
    @Override
    public Chat find(Integer id) {
        return getSession().createQuery("FROM Chat chat where id = :id", Chat.class)
                .setParameter("id", id).getSingleResult();
    }
    @Override
    public Integer save(Chat chat) {
        getSession().save(chat);
        return chat.getId();
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(find(id));
    }

    @Override
    public void update(Chat chat) {
        getSession().update(chat);
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
