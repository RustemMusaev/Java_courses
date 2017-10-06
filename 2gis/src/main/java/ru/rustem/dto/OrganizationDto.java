package ru.rustem.dto;

import ru.rustem.model.City;
import ru.rustem.model.Street;

import java.sql.Timestamp;

public class OrganizationDto {

    private String name;
    private City city;
    private Street street;
    private Integer houseNumber;
    private String description;
    private String website;
    private Timestamp dateUpdate;

    public OrganizationDto(String name, City city, Street street, Integer houseNumber, String description, String website, Timestamp dateUpdate) {

        this.name = name;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.website = website;
        this.dateUpdate = dateUpdate;
    }

    public OrganizationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

  }
