package ru.rustem.dto;

import lombok.*;
import ru.rustem.model.City;
import ru.rustem.model.Street;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OrganizationDto implements Serializable {

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

  }
