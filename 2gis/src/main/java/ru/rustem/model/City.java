package ru.rustem.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class City {

    private Integer id;
    private String name;
    private Double area;
    private Integer countPeople;

    public City(String name, Double area, Integer countPeople) {
        this.name = name;
        this.area = area;
        this.countPeople = countPeople;
    }

    public City(Integer id, String name, Double area, Integer countPeople) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.countPeople = countPeople;
    }
}
