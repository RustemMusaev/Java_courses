package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.model.Chat;
import ru.itis.model.Message;
import ru.itis.model.Session;

import java.util.List;

@Repository("MessageDao")
public class MessagesDaoImpl implements MessagesDao {
    private SessionFactory sessionFactory;
    @Autowired
    private MessagesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    public MessagesDaoImpl() {
    }

    @Override
    public List<Message> findAll() {
        return getSession().createQuery("FROM Message").list();
    }

    @Override
    public Message find(Integer id) {
        return getSession().createQuery("FROM Message message where id = :id", Message.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Integer save(Message message) {
        getSession().save(message);
        return message.getId();
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(find(id));
    }

    @Override
    public void update(Message chat) {
        getSession().update(chat);
    }

    @Override
    public List<Message> findAllByChatId(Integer chatId) {
        return getSession().createQuery("FROM Message message where chat = :id", Message.class)
                .setParameter("id", chatId).list();
    }
    private org.hibernate.Session getSession() {
        org.hibernate.Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }
}
