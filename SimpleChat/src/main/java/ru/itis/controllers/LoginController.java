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
import java.util.ArrayList;
import java.util.List;

import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithChatDTO;

@RestController
public class LoginController {
    @Autowired
    ChatUserService chatUserService;

    @PostMapping("/users")
    public ResponseEntity<ChatUserDto> signUp(@RequestBody ChatUserDataForRegistrationDto user) {
        ChatUserDto chatUserDto=chatUserService.registerUser(user);
        return new ResponseEntity<>(chatUserDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("password") String password,
                                        @RequestHeader("login") String login) {
        String token = chatUserService.login(password, login);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
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
}
