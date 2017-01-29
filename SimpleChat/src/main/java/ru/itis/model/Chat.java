package ru.itis.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    private Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable (name = "chatmember",
            joinColumns = @JoinColumn (name = "chat_id"),
            inverseJoinColumns = @JoinColumn (name = "chatuser_id"))
    private Set<ChatUser> chatUserSet;

    public Chat(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.chatUserSet=builder.chatUserSet;
    }

    public Chat() {
    }

    public Integer getId() {
        return null;
    }

    public String getName() {
        return name;
    }

    public Set<ChatUser> getChatUserSet() {
        return chatUserSet;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Set<ChatUser> chatUserSet=new HashSet<ChatUser>();

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder chatUserSet(Set<ChatUser> chatUserSet) {
            this.chatUserSet = chatUserSet;
            return this;
        }
        public Chat build() {
            return new Chat(this);
        }
    }
}
