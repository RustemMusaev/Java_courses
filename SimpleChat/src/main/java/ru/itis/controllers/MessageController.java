package ru.itis.controllers;

import antlr.debug.MessageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.itis.converters.MessageToMessageDtoConverter;
import ru.itis.dto.MessageDto;
import ru.itis.model.ChatUser;
import ru.itis.model.Message;
import ru.itis.service.ChatService;
import ru.itis.service.ChatUserService;
import ru.itis.service.MessageService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static ru.itis.converters.MessageToMessageDtoConverter.convertMessageDtoWithoutChatUserWithoutChat;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatUserService chatUserService;
    @Autowired
    SimpMessagingTemplate template;
/*Написать сообщение в чат: POST /chats/{chat-id}/messages
MessageDto – на GET: {id-сообщения, text, name-пользователя}, в POST – только text
*/
    @PostMapping("/chats/{chat-id}/messages")
    public void messageToChat(@PathVariable("chat-id") Integer chat_id,
                                          @RequestBody MessageDto messageDto,@RequestHeader("Auth-Token") String token) {
        ChatUser chatUser=chatUserService.findByToken(token);
        Message message=new Message.Builder().chat(chatService.find(chat_id))
                .chatUser(chatUser)
                .text(messageDto.getText())
                .build();
        messageService.save(message);
        MessageDto messageSend=convertMessageDtoWithoutChatUserWithoutChat(message);
        template.convertAndSend("/topic/"+chat_id+"/messages",messageSend);
    }
 /*   Поулчить все сообщения GET /chats/{chat-id}/messages?get=all
•*/
    @GetMapping("/chats/{chat-id}/messages")
    public  ResponseEntity<List<MessageDto>> getAllMessage(@PathVariable("chat-id") Integer chat_id,
                                                           @RequestParam("get") String get,@RequestHeader("Auth-Token") String token) {
        if (get.equals("all")) {
            List<MessageDto> messageListDto = messageService.findAllByChatId(chat_id).stream()
                    .map(MessageToMessageDtoConverter::convertMessageDtoWithoutChatUserWithoutChat).collect(Collectors.toList());
            return new ResponseEntity<List<MessageDto>>(messageListDto, HttpStatus.OK);
        } else if (get.equals("new")) {
            List<MessageDto> messageListDto = messageService.findNewByChatId(chat_id,chatUserService.findByToken(token).getId()).stream()
                    .map(MessageToMessageDtoConverter::convertMessageDtoWithoutChatUserWithoutChat).collect(Collectors.toList());
            return new ResponseEntity<List<MessageDto>>(messageListDto, HttpStatus.OK);
        } else {
            List<MessageDto> messageListDto = messageService.findAllByChatId(chat_id).stream()
                    .map(MessageToMessageDtoConverter::convertMessageDtoWithoutChatUserWithoutChat).collect(Collectors.toList());
            return new ResponseEntity<List<MessageDto>>(messageListDto, HttpStatus.OK);
        }
    }
}
