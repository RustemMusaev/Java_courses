package ru.itis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.model.ChatUser;


public interface SessionService {
    ChatUser findUserByToken (String token);
    void deleteSessionByToken(String token);
}
