package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.itis.model.BaseModel;
import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto implements BaseModel {
    private Integer id;
    private ChatDto chatDto;
    private ChatUserDto chatUserDto;
    private String text;

    public MessageDto() {
    }

    public MessageDto(Builder builder) {
        this.id= builder.id;
        this.chatDto = builder.chatDto;
        this.chatUserDto = builder.chatUserDto;
        this.text = builder.text;
    }


    @Override
    public Integer getId() {
        return null;
    }

    public ChatDto getChatDto() {
        return chatDto;
    }

    public ChatUserDto getChatUserDto() {
        return chatUserDto;
    }

    public String getText() {
        return text;
    }

    public static class Builder {
        private Integer id;
        private ChatDto chatDto;
        private ChatUserDto chatUserDto;
        private String text;

        public Builder chatDto(ChatDto chatDto) {
            this.chatDto = chatDto;
            return this;
        }
        public Builder id(Integer id) {
            this.id= id;
            return this;
        }

        public Builder chatUserDto(ChatUserDto chatUserDto) {
            this.chatUserDto = chatUserDto;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public MessageDto build() {
            return new MessageDto(this);
        }
    }
}
