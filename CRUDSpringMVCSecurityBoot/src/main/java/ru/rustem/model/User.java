package ru.rustem.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(AccessType.FIELD)
    private Integer id;

    @Column(name = "name")
    @Access(AccessType.FIELD)
    @NotEmpty(message = "{user.name.notNull}")
    private String name;

    @Column(name = "surname")
    @Access(AccessType.FIELD)
    @NotEmpty(message = "{user.surname.notNull}")
    private String surname;

    @Column(name = "birthday")
    @Access(AccessType.FIELD)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{user.birthday.notNull}")
    private Date birthday;

    @Column(name = "login")
    @Access(AccessType.FIELD)
    @NotEmpty(message = "{user.login.notNull}")
    private String login;

    @Column(name = "password")
    @Access(AccessType.FIELD)
    @NotNull(message = "{user.password.notNull}")
    @Size(min = 8, message = "{user.password.size}")
    private String password;

    @Column(name = "about_me")
    @Access(AccessType.FIELD)
    private String aboutMe;

    @Column(name = "address")
    @Access(AccessType.FIELD)
    @NotEmpty(message = "{user.address.notNull}")
    private String address;

    @Column(name = "token")
    @Access(AccessType.FIELD)
    private String token;

    public User() {
    }

    public User(String name, String surname, Date birthday, String login, String password, String aboutMe, String address) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.aboutMe = aboutMe;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
