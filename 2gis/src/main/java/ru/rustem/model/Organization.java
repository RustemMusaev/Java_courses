package ru.rustem.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Organization {

    private Integer id;
    private String name;
    private City city;
    private Street street;
    private Integer houseNumber;
    private String description;
    private String website;
    private Timestamp dateUpdate;

    public Organization(String name, City city, Street street, Integer houseNumber, String description, String website, Timestamp dateUpdate) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.website = website;
        this.dateUpdate = dateUpdate;
    }

    public Organization(Integer id, String name, City city, Street street, Integer houseNumber, String description, String website, Timestamp dateUpdate) {

        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.website = website;
        this.dateUpdate = dateUpdate;
    }
}
