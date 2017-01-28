package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatUsersDao;
import ru.itis.dao.ChatsDao;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatsDao chatsDao;


    @Override
    public List<Chat> findAll() {
        return chatsDao.findAll();
    }

    @Override
    public Chat find(Integer id) {
        return chatsDao.find(id);
    }

    @Override
    public Integer save(Chat chat) {
        return chatsDao.save(chat);
    }

    @Override
    public void delete(Integer id) {
        chatsDao.delete(id);
    }

    @Override
    public void update(Chat chat) {
        chatsDao.update(chat);
    }
}