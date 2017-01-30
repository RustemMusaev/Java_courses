package ru.itis.controllers;

import antlr.debug.MessageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.converters.MessageToMessageDtoConverter;
import ru.itis.dto.MessageDto;
import ru.itis.model.ChatUser;
import ru.itis.model.Message;
import ru.itis.service.ChatService;
import ru.itis.service.ChatUserService;
import ru.itis.service.MessageService;

import java.util.List;
import java.util.stream.Collectors;

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
    private MessageService messageService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatUserService chatUserService;

    @PostMapping("/chats/{chat-id}/messages")
    public ResponseEntity<MessageDto> messageToChat(@PathVariable("chat-id") Integer chat_id,
                                          @RequestBody MessageDto messageDto,@RequestHeader("Auth-Token") String token) {
        ChatUser chatUser=chatUserService.findByToken(token);
        Message message=new Message.Builder().chat(chatService.find(chat_id)).chatUser(chatUser).text(messageDto.getText()).build();
        messageService.save(message);
        MessageDto messageSend=convertMessageDtoWithoutChatUserWithoutChat(message);
        return new ResponseEntity<MessageDto>(messageSend, HttpStatus.OK);
    }
    @GetMapping("/chats/{chat-id}/messages")
    public  ResponseEntity<List<MessageDto>> getAllMessage(@PathVariable("chat-id") Integer chat_id, @RequestHeader("Auth-Token") String token){
        List<MessageDto> messageListDto=messageService.findAllByChatId(chat_id).stream()
                .map(MessageToMessageDtoConverter::convertMessageDtoWithoutChatUserWithoutChat).collect(Collectors.toList());
        return new ResponseEntity<List<MessageDto>>(messageListDto,HttpStatus.OK);
    }
}
