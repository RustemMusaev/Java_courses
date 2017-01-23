package ru.itis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    private Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable (name = "chatmember",
            joinColumns = @JoinColumn (name = "chat_id"),
            inverseJoinColumns = @JoinColumn (name = "chatuser_id"))
    private List<ChatUser> chatUserList;

    public Chat(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.chatUserList=builder.chatUserList;
    }

    public Chat() {
    }

    public Integer getId() {
        return null;
    }

    public String getName() {
        return name;
    }

    public List<ChatUser> getChatUserList() {
        return chatUserList;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private List<ChatUser> chatUserList;


        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder chatUserList(List<ChatUser> chatUserList) {
            this.chatUserList = chatUserList;
            return this;
        }


        public Chat build() {
            return new Chat(this);
        }
    }
}
