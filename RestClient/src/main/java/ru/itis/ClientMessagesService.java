package ru.itis;


import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientMessagesService {

    private RestClient restClient;
    private ExecutorService executorService;

    public ClientMessagesService(){
        restClient=new RestClient();
        executorService= Executors.newFixedThreadPool(1);
    }
    private void messagesHandle() {
       Runnable handleMessagesTask = () -> {
            while(true) {
                MessageDto[] messages = restClient.getMessages();
                for (MessageDto currentMessage : messages) {
                    System.out.println(currentMessage.getMessage());
                }
            }
        };
        executorService.submit(handleMessagesTask);
    }

    public void messagesProcessing() {
        messagesHandle();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String text = scanner.nextLine();
            MessageDto messageDto = new MessageDto.Builder().message(text).from("ot Rustem").build();
            restClient.sendMessages(messageDto);
        }
    }

}
