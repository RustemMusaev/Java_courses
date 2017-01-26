package ru.itis;

import javax.persistence.*;

@Entity
@Table(name = "chatuser")
public class ChatUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    private Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "login")
    private String login;

    @Access(AccessType.FIELD)
    @Column(name = "password_hash")
    private String password_hash;

    public ChatUser() {
    }

    public ChatUser(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password_hash = builder.password_hash;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword_hash() {
        return password_hash;
    }


    public static class Builder {
        private Integer id;
        private String login;
        private String password_hash;

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

        public ChatUser builder() {
            return new ChatUser(this);
        }
    }
}
