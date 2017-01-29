package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.MessageDto;
import ru.itis.model.ChatUser;
import ru.itis.model.Message;
import ru.itis.service.ChatService;
import ru.itis.service.MessageService;
import ru.itis.service.SessionService;

import static ru.itis.converters.MessageToMessageDtoConverter.convertMessageDtoWithoutChatUserWithoutChat;

@RestController
public class MessageController {
    /*
•	Получить сообщения из чата: LONG Pooling GET /chats/{chat-id}/messages
•	Поулчить все сообщения GET /chats/{chat-id}/messages?get=all
•	Написать сообщение в чат: POST /chats/{chat-id}/messages
MessageDto – на GET: {id-сообщения, text, name-пользователя}, в POST – только text
*/
    @Autowired
    private SessionService sessionService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;

    @PostMapping("/chats/{chat-id}/messages")
    public ResponseEntity<MessageDto> messageToChat(@PathVariable("chat-id") Integer chat_id,
                                          @RequestBody MessageDto messageDto,@RequestHeader("Auth-Token") String token) {
        ChatUser chatUser=sessionService.findUserByToken(token);
        Message message=new Message.Builder().chat(chatService.find(chat_id)).chatUser(chatUser).text(messageDto.getText()).build();
        messageService.save(message);
        MessageDto messageSend=convertMessageDtoWithoutChatUserWithoutChat(message);
        return new ResponseEntity<MessageDto>(messageSend, HttpStatus.OK);
    }
}
