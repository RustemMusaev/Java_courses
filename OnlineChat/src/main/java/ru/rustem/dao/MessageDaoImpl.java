package ru.rustem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rustem.model.Message;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * This class implements method MessageDao interface
 */
@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {
    private SessionFactory sessionFactory;

    @Autowired
    public MessageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method save message on database
     *
     * @param message - message for need save
     * @return message ID, after completed opeation
     */
    @Override
    public Integer save(Message message) {
        getSession().saveOrUpdate(message);
        return message.getId();
    }

    /**
     * This method find all message
     *
     * @return all message of database
     */
    @Override
    public List<Message> findAll() {
        return getSession().createQuery("FROM Message").list();
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
