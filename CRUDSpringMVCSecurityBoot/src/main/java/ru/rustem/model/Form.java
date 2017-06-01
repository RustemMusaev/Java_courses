package ru.rustem.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "registers")
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Size(min = 2, max = 10, message = "Your name should be between 5 - 10 characters.")
    private String name;

    @Column(name = "phone")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Your phone should be between 10 number")
    private String phone;

    @Column(name = "email")
    @Size(max = 40, message = "limit characters")
    @Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
    private String email;

    public Form() {
    }

    public Form(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
