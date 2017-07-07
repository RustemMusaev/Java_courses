package ru.rustem.model;

public class Point {
    private String city;
    private String country;
    private String address;
    private String name;
    private String phone;
    private String services;

    public Point(String city, String country, String address, String name, String phone, String services) {
        this.city = city;
        this.country = country;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.services = services;
    }

    public Point() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
