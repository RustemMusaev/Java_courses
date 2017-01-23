package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto{

    private Integer id;
    private String name;
    private List<ChatUserDto> chatUserDtoList;

    public ChatDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.chatUserDtoList =builder.chatUserDtoList;
    }

    public ChatDto() {
    }

    public Integer getId() {
        return null;
    }

    public String getName() {
        return name;
    }

    public List<ChatUserDto> getChatUserDtoList() {
        return chatUserDtoList;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private List<ChatUserDto> chatUserDtoList;


        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder chatUserDtoList(List<ChatUserDto> chatUserDtoList) {
            this.chatUserDtoList = chatUserDtoList;
            return this;
        }


        public ChatDto build() {
            return new ChatDto(this);
        }
    }
}
