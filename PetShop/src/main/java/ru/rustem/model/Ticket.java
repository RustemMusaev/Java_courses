package ru.rustem.model;

/**
 * Created by musaevrr on 07.06.2017.
 */
public class Ticket {
    private Integer id;
    private String email;
    private String phone;
    private boolean status;

    public Ticket() {
    }

    public Ticket(String email, String phone, boolean status) {
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Ticket(Integer id, String email, String phone, boolean status) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
