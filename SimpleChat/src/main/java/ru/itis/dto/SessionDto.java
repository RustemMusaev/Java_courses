package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.itis.model.BaseModel;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDto{
    Integer id;
    String token;
    ChatUserDto chatUserDto;
    Integer messageLastId;

    public SessionDto() {
    }

    public SessionDto(Builder builder) {
        this.id=builder.id;
        this.token = builder.token;
        this.chatUserDto = builder.chatUserDto;
        this.messageLastId = builder.messageLastId;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public ChatUserDto getChatUserDto() {
        return chatUserDto;
    }

    public Integer getMessage_last_id() {
        return messageLastId;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private ChatUserDto chatUserDto;
        private Integer messageLastId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder chatUserDto(ChatUserDto chatUserDto) {
            this.chatUserDto = chatUserDto;
            return this;
        }

        public Builder message_last_id(Integer messageLastId) {
            this.messageLastId = messageLastId;
            return this;
        }

        public SessionDto build() {
            return new SessionDto(this);
        }
    }
}
