package ru.itis.converters;

import ru.itis.dto.MessageDto;
import ru.itis.model.Message;

import static ru.itis.converters.ChatToChatDtoConverter.convertChatDtoWithoutChatUser;
import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithoutChatDTO;

public class MessageToMessageDtoConverter {
    public static MessageDto convertMessageDtoWithoutChatUserWithoutChat(Message message) {
       return new MessageDto.Builder().id(message.getId()).text(message.getText()).build();
    }
    public static MessageDto convertMessageDtoWithChatUserWithoutChat(Message message) {
        return new MessageDto.Builder().id(message.getId()).text(message.getText())
                .chatUserDto(convertChatUserDtoWithoutChatDTO(message.getChatUser())).build();
    }
    public static MessageDto convertMessageDtoWithoutChatUserWithChat(Message message) {
        return new MessageDto.Builder().id(message.getId()).text(message.getText())
                .chatUserDto(convertChatUserDtoWithoutChatDTO(message.getChatUser())).build();
    }
}
