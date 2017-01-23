package ru.itis.converters;

import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.util.ArrayList;

import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithoutChatDTO;

public class ChatToChatDtoConverter {
    public static ChatDto convertChatDtoWithoutChatUser(Chat chat) {
       return new ChatDto.Builder().id(chat.getId()).name(chat.getName()).build();
    }
    public static ChatDto convertChatDtoWithChatUser(Chat chat) {
        ChatDto chatDto = new ChatDto.Builder().id(chat.getId()).name(chat.getName())
                .chatUserDtoList(new ArrayList<ChatUserDto>()).build();
        for (ChatUser chatUser:chat.getChatUserList()){
            ChatUserDto chatUserDto= convertChatUserDtoWithoutChatDTO(chatUser);
            chatDto.getChatUserDtoList().add(chatUserDto);
        }
        return chatDto;
    }
}
