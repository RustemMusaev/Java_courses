package ru.rustem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rustem.model.Message;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {
    private SessionFactory sessionFactory;
    @Autowired
    public MessageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer save(Message message) {
        getSession().saveOrUpdate(message);
        return message.getId();
    }

    @Override
    public List<Message> findAll() {
        return getSession().createQuery("FROM Message").list();
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        //  session = sessionFactory.openSession();
        return session;
    }
}
