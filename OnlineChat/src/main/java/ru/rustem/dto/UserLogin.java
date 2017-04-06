package ru.rustem.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class UserLogin {
    @JsonView
    private String username;
    @JsonView
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



    public UserLogin() {
    }
}
