package com.mySampleApplication.server.model;

import java.io.Serializable;

public class Point implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String city;
    private String country;
    private String address;
    private String name;
    private String phone;
    private int services;

    public Point(String city, String country, String address, String name, String phone, int services) {
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

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (getServices() != point.getServices()) return false;
        if (getId() != null ? !getId().equals(point.getId()) : point.getId() != null) return false;
        if (getCity() != null ? !getCity().equals(point.getCity()) : point.getCity() != null) return false;
        if (getCountry() != null ? !getCountry().equals(point.getCountry()) : point.getCountry() != null) return false;
        if (getAddress() != null ? !getAddress().equals(point.getAddress()) : point.getAddress() != null) return false;
        if (getName() != null ? !getName().equals(point.getName()) : point.getName() != null) return false;
        return getPhone() != null ? getPhone().equals(point.getPhone()) : point.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + getServices();
        return result;
    }
}
