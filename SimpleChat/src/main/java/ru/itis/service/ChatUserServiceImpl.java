package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatUsersDao;
import ru.itis.model.ChatUser;

import java.util.List;

@Service("chatUserService")
@Transactional
public class ChatUserServiceImpl implements ChatUserService {

    @Autowired
    private ChatUsersDao chatUsersDao;

    @Override
    public ChatUser find(Integer id) {
        return chatUsersDao.find(id);
    }

    @Override
    public List<ChatUser> findAll() {
        return chatUsersDao.findAll();
    }

    @Override
    public Integer save(ChatUser chatUser) {
        return chatUsersDao.save(chatUser);
    }

    @Override
    public void update(ChatUser chatUser) {
        chatUsersDao.save(chatUser);
    }

    @Override
    public void delete(Integer id) {
        chatUsersDao.delete(id);
    }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {
        chatUsersDao.saveUserToChat(userId,chatId);
    }
}