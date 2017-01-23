package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.ChatUsersDao;
import ru.itis.model.ChatUser;

import java.util.List;

@Service
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
    public void save(ChatUser chatUser) {
        chatUsersDao.save(chatUser);
    }

    @Override
    public void update(ChatUser chatUser) {
        chatUsersDao.save(chatUser);
    }

    @Override
    public void delete(Integer id) {
        chatUsersDao.delete(id);
    }
}