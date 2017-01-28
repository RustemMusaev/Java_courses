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

    public Session() {
    }

    public Session(Builder builder) {
        this.id=builder.id;
        this.token = builder.token;
        this.chatUser = builder.chatUser;
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

    public static class Builder {
        private Integer id;
        private String token;
        private ChatUser chatUser;

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

         public Session build() {
            return new Session(this);
        }
    }
}
