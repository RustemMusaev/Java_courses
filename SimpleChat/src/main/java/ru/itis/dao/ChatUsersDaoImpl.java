package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.util.List;

@Repository("ChatUsersDao")
public class ChatUsersDaoImpl implements ChatUsersDao {

    private SessionFactory sessionFactory;
    @Autowired
    public ChatUsersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ChatUsersDaoImpl() {
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
    public Integer save(ChatUser chatUser) {
        getSession().saveOrUpdate(chatUser);
        return chatUser.getId();
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(find(id));
        }

    @Override
    public void update(ChatUser chatUser) {
        getSession().saveOrUpdate(chatUser);
        }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {
        Chat chat=getSession().createQuery("FROM Chat chat where id = :id", Chat.class)
                .setParameter("id", chatId).getSingleResult();
        ChatUser chatUser=find(userId);
       chatUser.getChatSet().add(chat);
    }

    @Override
    public ChatUser findByLogin(String login) {
        return getSession().createQuery("FROM ChatUser chatuser where login = :login", ChatUser.class)
                .setParameter("login", login).getSingleResult();
    }
    @Override
    public boolean isExistToken(String token) {
        return false;
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
