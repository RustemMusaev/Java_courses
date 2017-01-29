package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.MessagesDao;
import ru.itis.dto.MessageDto;
import ru.itis.model.Message;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessagesDao messagesDao;
    @Override
    public Integer save(Message message) {
        return messagesDao.save(message);
    }
}
