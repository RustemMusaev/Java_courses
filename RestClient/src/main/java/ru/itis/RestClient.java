package ru.itis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {

    private RestTemplate restTemplate;

    public RestClient() {
        restTemplate=new RestTemplate();
    }

    public void sendMessages(MessageDto messageDto){
        restTemplate.postForObject("http://localhost:8080/messages",messageDto,MessageDto.class);
    }

    public MessageDto[] getMessages(){
        ResponseEntity<MessageDto[]> responseEntity=restTemplate
                .getForEntity("http://localhost:8080/messages",MessageDto[].class);
        return responseEntity.getBody();
    }





}
