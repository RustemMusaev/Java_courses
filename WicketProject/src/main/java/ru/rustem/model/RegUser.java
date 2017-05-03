package ru.rustem.model;

import java.io.Serializable;

public class RegUser implements Serializable {
    String login;
    String password;
    String cpassword;


    public RegUser() {
    }

    public RegUser(String login, String password, String cpassword) {
        this.login = login;
        this.password = password;
        this.cpassword = cpassword;
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

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}
