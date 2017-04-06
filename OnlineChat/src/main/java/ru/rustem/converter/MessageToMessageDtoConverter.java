package ru.rustem.converter;

import ru.rustem.dto.MessageDto;
import ru.rustem.model.Message;

public class MessageToMessageDtoConverter {
    public static MessageDto messageToMessageDtoConverter(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setLogin(message.getUser().getLogin());
        messageDto.setText(message.getMessage());
        return messageDto;
    }
}
