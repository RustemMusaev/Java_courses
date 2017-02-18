package ru.itis.server.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "autos")
public class Auto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auto_model")
    private String model;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="auto_userId")
    private User user;

    public Auto(Builder builder) {
        this.model = builder.model;
        this.user = builder.user;
    }

    public Auto() {
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public User getUser() {
        return user;
    }

    public static class Builder {
        private String model;
        private User user;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Auto build() {
            return new Auto(this);
        }
    }

    @Override
    public String toString() {
        return this.getId()+" :: "+this.getModel()+" :: ";
    }
}
