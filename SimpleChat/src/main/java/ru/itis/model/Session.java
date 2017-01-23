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

    @Access(AccessType.FIELD)
    @Column(name = "chatuser_id")
    ChatUser chatUser;

    @Access(AccessType.FIELD)
    @Column(name = "message_last_id")
    Integer messageLastId;

    public Session() {
    }

    public Session(Builder builder) {
        this.id=builder.id;
        this.token = builder.token;
        this.chatUser = builder.chatUser;
        this.messageLastId = builder.messageLastId;
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

    public Integer getMessage_last_id() {
        return messageLastId;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private ChatUser chatUser;
        private Integer messageLastId;

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

        public Builder message_last_id(Integer messageLastId) {
            this.messageLastId = messageLastId;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}
