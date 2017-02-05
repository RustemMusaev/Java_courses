package ru.itis.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.model.Message;

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
        Message mas=message;
        Integer id=message.getId();
        Integer chatid = message.getChat().getId();
        Integer userid = message.getChatUser().getId();
        getSession().createNativeQuery("UPDATE chatmember SET message_last_id=? WHERE chatuser_id=? AND chat_id=?")
                .setParameter(1, id)
                .setParameter(2, userid)
                .setParameter(3, chatid)
                .executeUpdate();
        return id;
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(find(id));
    }

    @Override
    public void update(Message message) {
        getSession().update(message);
    }

    @Override
    public List<Message> findAllByChatId(Integer chatId) {
        return getSession().createQuery("FROM Message message where message.chat.id = :id", Message.class)
                .setParameter("id", chatId).list();
    }

    @Override
    public List<Message> findNewByChatId(Integer chatId,Integer userId) {
       Integer messageId =(Integer) getSession().createNativeQuery("SELECT * FROM chatmember WHERE chat_id=? AND chatuser_id=?")
                .setParameter(1, chatId)
                .setParameter(2, userId).addScalar("message_last_id", IntegerType.INSTANCE)
                .getSingleResult();

        if (messageId!=null){
        return getSession().createQuery("FROM Message m WHERE m.chat.id = :chatId AND m.id > :messageId",Message.class)
                    .setParameter("chatId",chatId).setParameter("messageId",messageId).list();
        } else {
            return findAllByChatId(chatId);
        }
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
