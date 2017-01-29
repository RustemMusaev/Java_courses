package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.SessionDao;
import ru.itis.model.ChatUser;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDao sessionDao;

    @Override
    public ChatUser findUserByToken(String token) {
        return sessionDao.findUserByToken(token);
    }

    @Override
    public void deleteSessionByToken(String token) {
        sessionDao.deleteSessionByToken(token);
    }
}
