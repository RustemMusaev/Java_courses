package ru.itis.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestServiceConfig {

    @Bean
    ChatUserServiceImpl chatUserService(){
        return new ChatUserServiceImpl();
    }
}
