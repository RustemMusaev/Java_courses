package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.itis.model.BaseModel;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatUserDto implements BaseModel {

    private Integer id;
    private String login;
    private String password_hash;
    private List<ChatDto> chatListDto;

    public ChatUserDto() {
    }

    public ChatUserDto(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password_hash = builder.password_hash;
        this.chatListDto = builder.chatDtoList;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
    public List<ChatDto> getchatListDto() {
        return chatListDto;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public static class Builder {
        private Integer id;
        private String login;
        private String password_hash;
        private List<ChatDto> chatDtoList;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder login(String login) {
            this.login = login;
            return this;
        }
        public Builder password_hash(String password_hash) {
            this.password_hash = password_hash;
            return this;
        }
        public Builder chatDtoList(List<ChatDto> chatDtoList) {
            this.chatDtoList = chatDtoList;
            return this;
        }

        public ChatUserDto builder() {
            return new ChatUserDto(this);
        }
    }
}
