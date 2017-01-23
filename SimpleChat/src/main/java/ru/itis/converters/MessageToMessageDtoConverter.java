package ru.itis.converters;

import ru.itis.dto.MessageDto;
import ru.itis.dto.SessionDto;
import ru.itis.model.Message;
import ru.itis.model.Session;

import static ru.itis.converters.ChatToChatDtoConverter.convertChatDtoWithoutChatUser;
import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithChatDTO;
import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithoutChatDTO;

public class MessageToMessageDtoConverter {
    public static MessageDto convertMessageDtoWithoutChatUserWithoutChat(Message message) {
       return new MessageDto.Builder().id(message.getId()).text(message.getText()).build();
    }
    public static MessageDto convertMessageDtoWithChatUserWithoutChat(Message message) {
        return new MessageDto.Builder().id(message.getId()).text(message.getText())
                .chatDto(convertChatDtoWithoutChatUser(message.getChat())).build();
    }
    public static MessageDto convertMessageDtoWithoutChatUserWithChat(Message message) {
        return new MessageDto.Builder().id(message.getId()).text(message.getText())
                .chatUserDto(convertChatUserDtoWithoutChatDTO(message.getChatUser())).build();
    }
}
