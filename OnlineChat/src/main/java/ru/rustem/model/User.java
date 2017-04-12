package ru.rustem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    private Integer id;
    @Access(AccessType.FIELD)
    @Column(name = "login")
    private String login;
    @Access(AccessType.FIELD)
    @Column(name = "passwordhash")
    private String passwordHash;
    @Access(AccessType.FIELD)
    @Column(name = "name")
    private String name;
    @Access(AccessType.FIELD)
    @Column(name = "surname")
    private String surname;
    @Access(AccessType.FIELD)
    @Column(name = "photo")
    private String photo;
    @Access(AccessType.FIELD)
    @Column(name = "phone")
    private String phone;
    @Access(AccessType.FIELD)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Message> messages = new HashSet<>();

    @Access(AccessType.FIELD)
    @Column(name = "token")
    private String token;


    public User() {
    }

    public User(String login, String passwordHash, String name, String surname, String photo, String phone, String email, Set<Message> messages, String token) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.messages = messages;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
