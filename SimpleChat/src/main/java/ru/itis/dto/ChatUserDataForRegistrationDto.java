package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ChatUserDataForRegistrationDto {
    private String login;
    private String password;

    public ChatUserDataForRegistrationDto(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
    }

    public ChatUserDataForRegistrationDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        private String login;
        private String password;

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public ChatUserDataForRegistrationDto builder() {
            return new ChatUserDataForRegistrationDto(this);
        }
    }
}
