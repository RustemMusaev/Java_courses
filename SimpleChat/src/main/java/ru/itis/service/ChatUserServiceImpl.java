package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatUsersDao;
import ru.itis.dto.ChatUserDataForRegistrationDto;
import ru.itis.dto.ChatUserDto;


import ru.itis.model.ChatUser;
import ru.itis.security.utils.TokenGenerator;
import java.util.List;

import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithoutChatDTO;

@Service
@Transactional
public class ChatUserServiceImpl implements ChatUserService {

    @Autowired
    private ChatUsersDao chatUsersDao;
    @Autowired
    private TokenGenerator generator;

    PasswordEncoder encoder=new BCryptPasswordEncoder();

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
    public ChatUser findByLogin(String login){
        return chatUsersDao.findByLogin(login);
    }
    @Override
    public ChatUser findByToken(String token) {
        return chatUsersDao.findByToken(token);
    }
    @Override
    public boolean isExistToken(String token) {
        return chatUsersDao.isExistToken(token);
    }
    @Override
    public boolean isExistLogin(String login) {
        return chatUsersDao.isExistLogin(login);
    }
    @Override
    public void saveToken(Integer user_id, String token) {
        chatUsersDao.saveToken(user_id,token);
    }
    @Override
    public void deleteToken(String token) {
        chatUsersDao.deleteToken(token);
    }
    @Override
    public ChatUserDto registerUser(ChatUserDataForRegistrationDto user) {
        ChatUser newUser = new ChatUser.Builder().login(user.getLogin())
                        .password_hash(encoder.encode(user.getPassword())).builder();
        ChatUser savedUser =chatUsersDao.find(chatUsersDao.save(newUser));
        return convertChatUserDtoWithoutChatDTO(savedUser);
    }

    @Override
    public String login(String password, String login) {
        // TODO: проверить, найден ли пользователь, проверка пароля!!!
        ChatUser registeredUser = chatUsersDao.findByLogin(login);
        System.out.println(encoder.encode(password));
        if (encoder.matches(password,registeredUser.getPassword_hash())) {
            String token = generator.generateToken();
            chatUsersDao.saveToken(registeredUser.getId(), token);
            return token;
        } else throw new IllegalArgumentException("Incorrect password");
    }
}