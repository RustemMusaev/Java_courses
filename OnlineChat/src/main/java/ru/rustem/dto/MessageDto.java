package ru.rustem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto {
    private String text;
    private String login;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
