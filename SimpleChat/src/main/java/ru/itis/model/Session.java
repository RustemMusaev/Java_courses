package ru.itis.model;

import javax.persistence.*;

@Entity
@Table(name = "session")
public class Session implements BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "id")
    Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "token")
    String token;

    @OneToOne
    @JoinColumn(name = "chatuser_id")
    ChatUser chatUser;

    @OneToOne
    @JoinColumn(name = "message_last_id")
    Message message;

    public Session() {
    }

    public Session(Builder builder) {
        this.id=builder.id;
        this.token = builder.token;
        this.chatUser = builder.chatUser;
        this.message = builder.message;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public Message getMessage() {
        return message;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private ChatUser chatUser;
        private Message message;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder chatUser(ChatUser chatUser) {
            this.chatUser = chatUser;
            return this;
        }

        public Builder message(Message message) {
            this.message = message;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}
