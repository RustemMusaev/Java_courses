package ru.itis.converters;

import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.dto.SessionDto;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;
import ru.itis.model.Session;

import java.util.ArrayList;

import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithChatDTO;
import static ru.itis.converters.ChatUserToChatUserDtoConverter.convertChatUserDtoWithoutChatDTO;

public class SessionToSessionDtoConverter {
    public static SessionDto convertSessionDtoWithoutChatUser(Session session) {
       return new SessionDto.Builder().id(session.getId()).token(session.getToken()).message(session.getMessage()).build();
    }
    public static SessionDto convertSessionDtoWithChatUserWithChat(Session session) {
        return new SessionDto.Builder().id(session.getId()).token(session.getToken()).message(session.getMessage())
                .chatUserDto(convertChatUserDtoWithChatDTO(session.getChatUser())).build();
    }
    public static SessionDto convertSessionDtoWithChatUserWithouChat(Session session) {
        return new SessionDto.Builder().id(session.getId()).token(session.getToken()).message(session.getMessage())
                .chatUserDto(convertChatUserDtoWithoutChatDTO(session.getChatUser())).build();
    }

}
