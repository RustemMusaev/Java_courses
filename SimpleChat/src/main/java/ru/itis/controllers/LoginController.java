package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ChatUserDataForRegistrationDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.model.ChatUser;
import ru.itis.service.ChatUserService;
import ru.itis.service.SessionService;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithChatDTO;
import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithoutChatDTO;

@RestController
public class LoginController {
 /*   •	POST /users – регистрация. В теле указываем имя, логин, пароль. В ответ приходит токен.
        Если логин не уникальный выслать 403 – запрещенно.
       •	POST /login, в хеадерах передаем логин и пароль.
        В ответ на сообщение приходит 200 – если пользователь авторизован и 401
         – если такого польователя нет. Вместе с 200 ответом в хеадере передаем токен.
          В теле передаем имя пользвателя и его id.
*/
    @Autowired
    private ChatUserService chatUserService;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/users")
    public ResponseEntity<ChatUserDto> signUp(@RequestBody ChatUserDataForRegistrationDto user) {
        ChatUserDto chatUserDto=chatUserService.registerUser(user);
        return new ResponseEntity<>(chatUserDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("password") String password,
                                        @RequestHeader("login") String login) {
        String token = chatUserService.login(password, login);
        ChatUserDto chatUserDto=convertChatUserDtoWithoutChatDTO(chatUserService.findByLogin(login));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<>(chatUserDto, headers, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ChatUserDto>> getUsers() {
        List<ChatUser> chatUserList = chatUserService.findAll();
        List<ChatUserDto> chatUserDtoList=new ArrayList<>();
        for (ChatUser chatUser:chatUserList){
            chatUserDtoList.add(convertChatUserDtoWithChatDTO(chatUser));
        }
        return new ResponseEntity<>(chatUserDtoList, HttpStatus.ACCEPTED);
    }
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("Auth-Token") String token) {
        sessionService.deleteSessionByToken(token);
        ChatUserDto chatUserDto=convertChatUserDtoWithoutChatDTO(sessionService.findUserByToken(token));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<>(chatUserDto, headers, HttpStatus.OK);
    }
}
