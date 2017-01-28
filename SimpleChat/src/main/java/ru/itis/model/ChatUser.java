package ru.itis.model;

import ru.itis.service.GenerateHashMd5;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chatuser")
public class ChatUser implements BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    private Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Access(AccessType.FIELD)
    @Column(name = "password_hash")
    private String password_hash;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name = "chatmember",
            joinColumns = @JoinColumn (name = "chatuser_id"),
            inverseJoinColumns = @JoinColumn (name = "chat_id"))
    private Set<Chat> chatSet=new HashSet<Chat>(0);

    public ChatUser() {
    }

    public ChatUser(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password_hash = builder.password_hash;
        this.chatSet = builder.chatSet;
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

    public Set<Chat> getChatSet() {
        return chatSet;
    }

    public static class Builder {
        private Integer id;
        private String login;
        private String password_hash;
        private Set<Chat> chatSet;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder login(String login) {
            this.login = login;
            return this;
        }
        public Builder password_hash(String password_hash) {
            this.password_hash = GenerateHashMd5.crypt(password_hash);
            return this;
        }
        public Builder chatSet(Set<Chat> chatSet) {
            this.chatSet = chatSet;
            return this;
        }

        public ChatUser builder() {
            return new ChatUser(this);
        }
    }
}
