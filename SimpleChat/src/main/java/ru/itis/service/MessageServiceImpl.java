package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.MessagesDao;
import ru.itis.dto.MessageDto;
import ru.itis.model.Message;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessagesDao messagesDao;

    @Override
    public List<Message> findAll() {
        return messagesDao.findAll();
    }

    @Override
    public Message find(Integer id) {
        return messagesDao.find(id);
    }

    @Override
    public Integer save(Message message) {
        return messagesDao.save(message);
    }

    @Override
    public void delete(Integer id) {
        messagesDao.delete(id);
    }

    @Override
    public void update(Message message) {
        messagesDao.update(message);
    }

    @Override
    public List<Message> findAllByChatId(Integer chatId) {
        return messagesDao.findAllByChatId(chatId);
    }

    @Override
    public List<Message> findNewByChatId(Integer chatId, Integer userId) {
        return messagesDao.findNewByChatId(chatId,userId);
    }
}
