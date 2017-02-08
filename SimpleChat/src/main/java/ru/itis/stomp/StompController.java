package ru.itis.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.MessageDto;

@RestController
public class StompController {

    @Autowired
    SimpMessagingTemplate template;

    //@MessageMapping("/chat")
    public void getMessage(MessageDto messageDto) {
        System.out.println("Get message " + messageDto.getText() +  " from " + messageDto.getText());
        template.convertAndSend("/topic/messages", new MessageDto.Builder().text("ertet").build());
    }
}
