package com.mySampleApplication.client.model;


import java.io.Serializable;

public class PointDto implements Serializable {
    private String address;
    private String name;
    private String phone;

    public PointDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointDto)) return false;

        PointDto pointDto = (PointDto) o;

        if (getAddress() != null ? !getAddress().equals(pointDto.getAddress()) : pointDto.getAddress() != null)
            return false;
        if (getName() != null ? !getName().equals(pointDto.getName()) : pointDto.getName() != null) return false;
        return getPhone() != null ? getPhone().equals(pointDto.getPhone()) : pointDto.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }
}
