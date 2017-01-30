package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        ChatUser chatUser= getSession().createQuery("FROM ChatUser chatuser where chatuser.login = :login", ChatUser.class)
                .setParameter("login", login).getSingleResult();
        return chatUser;
            }
    @Override
    public ChatUser findByToken(String token) {
        ChatUser chatUser = getSession().createQuery("from ChatUser u where :token in elements(u.token)",ChatUser.class)
                .setParameter("token",token).getSingleResult();
    return chatUser;}
    @Override
    public boolean isExistToken(String token) {
        return getSession().createQuery("from ChatUser u where :token in elements(u.token)")
                .setParameter("token",token).uniqueResult()!=null;
    }
    @Override
    public boolean isExistLogin(String login) {
        Query query=getSession().createQuery("from ChatUser user WHERE user.login= :login");
        query.setParameter("login",login);
        return (query.uniqueResult()!=null);
    }
    @Override
    public void saveToken(Integer user_id, String token) {
        ChatUser chatUser=find(user_id);
        chatUser.getToken().add(token);
    }
    @Override
    public void deleteToken(String token) {
        findByToken(token).getToken().remove(token);
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
