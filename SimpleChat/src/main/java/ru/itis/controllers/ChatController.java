package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;
import ru.itis.model.Message;
import ru.itis.service.ChatService;
import ru.itis.service.ChatUserService;
import ru.itis.service.MessageService;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.converters.ChatToChatDtoConverter.convertChatDtoWithoutChatUser;
import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithChatDTO;

@RestController
public class ChatController {
/*
•	Получить сообщения из чата: LONG Pooling GET /chats/{chat-id}/messages
•	Поулчить все сообщения GET /chats/{chat-id}/messages?get=all
•	Написать сообщение в чат: POST /chats/{chat-id}/messages
MessageDto – на GET: {id-сообщения, text, name-пользователя}, в POST – только text
*/
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatUserService chatUserService;
/*  Создать чат: POST /chats, в теле передаем название чата. Указываем в заголовке токен. В ответ приходит – id чата и 201.
*/
    @PostMapping("/chats")
    public ResponseEntity<ChatDto> addChat(@RequestBody ChatDto chatDtoName, @RequestHeader("Auth-Token") String token) {
        Chat chat=new Chat.Builder().name(chatDtoName.getName()).build();
        chatService.save(chat);
        return new ResponseEntity<>(convertChatDtoWithoutChatUser(chat), HttpStatus.OK);
    }
    /*  получить список всех чатов GET /chats, приходит массив ChatDto {id-чата, name-название чата}*/
    @GetMapping("/chats")
    public ResponseEntity<List<ChatDto>> getChat() {
        List<Chat> chatList=chatService.findAll();
        List<ChatDto> chatDtoList=new ArrayList<>();
        for (Chat chat:chatList){
            chatDtoList.add(convertChatDtoWithoutChatUser(chat));
        }
        return new ResponseEntity<>(chatDtoList, HttpStatus.OK);
    }
/*Зайти в существующий чат: POST /chats/{chat-id}/members, тело запроса пустое. В ответ приходит 200 – если все хорошо.
*/
    @PostMapping("/chats/{chat-id}/members")
    public ResponseEntity<ChatUserDto> addUserToChat(@PathVariable("chat-id") Integer chat_id, @RequestHeader("Auth-Token") String token) {
        ChatUser chatUser=chatUserService.findByToken(token);
        chatUserService.saveUserToChat(chatUser.getId(),chat_id);
        return new ResponseEntity<ChatUserDto>(convertChatUserDtoWithChatDTO(chatUser), HttpStatus.OK);
    }


}
