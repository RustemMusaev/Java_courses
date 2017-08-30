package ru.rustem.rambler.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "password_id")
    private Integer passwordId;

    public User() {
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


    public Integer getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Integer passwordId) {
        this.passwordId = passwordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
