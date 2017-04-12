package ru.rustem.converter;

import ru.rustem.dto.MessageDto;
import ru.rustem.model.Message;

/**
 * Message for return client, without Id and user, only login and text
 */
public class MessageToMessageDtoConverter {
    public static MessageDto messageToMessageDtoConverter(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setLogin(message.getUser().getLogin());
        messageDto.setText(message.getMessage());
        messageDto.setDate(message.getDate());
        return messageDto;
    }
}
