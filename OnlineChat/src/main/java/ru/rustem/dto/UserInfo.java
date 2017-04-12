package ru.rustem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    private String login;
    private String name;
    private String surname;
    private String photo;
    private String phone;
    private String email;

    public UserInfo() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof UserInfo)) {
            return false;
        }
        UserInfo userInfo = (UserInfo) object;
        return Objects.equals(name, userInfo.getName()) &&
                Objects.equals(surname, userInfo.getSurname()) &&
                Objects.equals(login, userInfo.getLogin()) &&
                Objects.equals(phone, userInfo.getPhone()) &&
                Objects.equals(photo, userInfo.getPhoto()) &&
                Objects.equals(email, userInfo.getEmail());

    }
}
