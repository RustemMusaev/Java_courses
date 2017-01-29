package ru.itis.service;

import ru.itis.dto.ChatUserDataForRegistrationDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.model.ChatUser;

import java.util.List;

public interface ChatUserService {
    ChatUser find(Integer id);
    List<ChatUser> findAll();
    Integer save(ChatUser chatUser);
    void update(ChatUser chatUser);
    void delete(Integer id);
    ChatUser findByLogin(String login);
    void saveUserToChat(Integer userId, Integer chatId);
    ChatUserDto registerUser (ChatUserDataForRegistrationDto chatUserDataForRegistrationDto);
    String login(String password,String login);
}