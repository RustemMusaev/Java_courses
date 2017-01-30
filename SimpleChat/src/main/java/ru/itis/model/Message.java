package ru.itis.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatuser_id")
    private ChatUser chatUser;

    @Access(AccessType.FIELD)
    @Column(name = "text")
    private String text;

    public Message(Builder builder) {
        this.id= builder.id;
        this.chat = builder.chat;
        this.chatUser = builder.chatUser;
        this.text = builder.text;
    }

    public Message() {
    }


    @Override
    public Integer getId() {
        return id;
    }

    public Chat getChat() {
        return chat;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public String getText() {
        return text;
    }

    public static class Builder {
        private Integer id;
        private Chat chat;
        private ChatUser chatUser;
        private String text;

        public Builder chat(Chat chat) {
            this.chat = chat;
            return this;
        }
        public Builder id(Integer id) {
            this.id= id;
            return this;
        }

        public Builder chatUser(ChatUser chatUser) {
            this.chatUser = chatUser;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
