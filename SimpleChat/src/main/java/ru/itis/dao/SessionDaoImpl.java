package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.model.ChatUser;
import ru.itis.model.Session;

import java.util.List;

@Repository("SessionDao")
public class SessionDaoImpl implements SessionDao {

    private SessionFactory sessionFactory;
    @Autowired
    public SessionDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public SessionDaoImpl() {
    }

    @Override
    public List<Session> findAll() {
        return getSession().createQuery("FROM Session ").list();
    }

    @Override
    public Session find(Integer id) {
        return getSession().createQuery("FROM Session session where id = :id", Session.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Integer save(Session session) {
        getSession().save(session);
        return session.getId();
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(find(id));
    }

    @Override
    public void update(Session chat) {
        getSession().saveOrUpdate(chat);
    }

    @Override
    public boolean isExistsToken(String token) {
        if (getSession().createQuery("FROM Session session where session.token = :token", Session.class)
                .setParameter("token", token).getSingleResult().equals(null)) {
            return false;
        } else return true;
    }

    @Override
    public ChatUser findUserByToken(String token) {
        Session session=getSession().createQuery("FROM Session session where token = :token", Session.class)
                .setParameter("token", token).getSingleResult();
        return getSession().createQuery("FROM ChatUser chatUser where id = :id", ChatUser.class)
                .setParameter("id", session.getChatUser().getId()).getSingleResult();
    }

    @Override
    public void addToken(Integer user_id, String token) {
        ChatUser chatUser=getSession().createQuery("FROM ChatUser chatUser where id = :id", ChatUser.class)
                .setParameter("id", user_id).getSingleResult();
        if(getSession().createQuery("FROM Session session where session.chatUser.id = :user_id", Session.class)
                .setParameter("user_id", user_id).getSingleResult()!=null){
            Session session=getSession().createQuery("FROM Session session where session.chatUser.id = :user_id", Session.class)
                    .setParameter("user_id", user_id).getSingleResult();
            update(new Session.Builder().id(session.getId()).token(token).chatUser(chatUser).build());
        } else {
            save(new Session.Builder().token(token).chatUser(chatUser).build());
        }
    }

    @Override
    public void deleteSessionByToken(String token) {
        Session session=getSession().createQuery("FROM Session session where session.token = :token", Session.class)
                .setParameter("token", token).getSingleResult();
        getSession().delete(session);
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
