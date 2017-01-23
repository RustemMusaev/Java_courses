package ru.itis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chatuser")
public class ChatUser implements BaseModel{
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

    @ManyToMany
    @JoinTable (name = "chatmember",
            joinColumns = @JoinColumn (name = "chatuser_id"),
            inverseJoinColumns = @JoinColumn (name = "chat_id"))
    private List<Chat> chatList;

    public ChatUser() {
    }

    public ChatUser(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password_hash = builder.password_hash;
        this.chatList = builder.chatList;
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

    public List<Chat> getChatList() {
        return chatList;
    }

    public static class Builder {
        private Integer id;
        private String login;
        private String password_hash;
        private List<Chat> chatList;

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
        public Builder chatList(List<Chat> chatList) {
            this.chatList = chatList;
            return this;
        }

        public ChatUser builder() {
            return new ChatUser(this);
        }
    }
}
