package ru.itis.server.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "group_member")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_age")
    private String age;
    @OneToMany
    @JoinColumn(name = "auto_userId", referencedColumnName = "user_id")
    private List<Auto> autos;

    public User() {
    }

    public User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.autos = builder.autos;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public static class Builder {
        private String name;
        private String age;
        private List<Auto> autos;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder autos(List<Auto> autos) {
            this.autos = autos;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
    @Override
    public String toString() {
        return this.getId()+" :: "+this.getName()+" :: "+this.getAge();
    }
}
