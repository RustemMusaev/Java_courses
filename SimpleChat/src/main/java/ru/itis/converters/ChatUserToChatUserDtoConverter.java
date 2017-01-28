package ru.itis.converters;

import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.util.ArrayList;

import static ru.itis.converters.ChatToChatDtoConverter.convertChatDtoWithoutChatUser;


public class ChatUserToChatUserDtoConverter {
    public static ChatUserDto convertChatUserDtoWithoutChatDTO(ChatUser chatUser) {
       return new ChatUserDto.Builder().id(chatUser.getId()).login(chatUser.getLogin()).builder();
    }
    public static ChatUserDto convertChatUserDtoWithChatDTO(ChatUser chatUser) {
        ChatUserDto chatUserDto = new ChatUserDto.Builder().id(chatUser.getId()).login(chatUser.getLogin())
                .password_hash(chatUser.getPassword_hash()).chatDtoList(new ArrayList<ChatDto>()).builder();
        for (Chat chat:chatUser.getChatSet()){
            ChatDto chatDto= convertChatDtoWithoutChatUser(chat);
            chatUserDto.getchatListDto().add(chatDto);
        }
        return chatUserDto;
    }
}
