package ru.rustem.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @Builder
public class CityDto implements Serializable {
    private String name;
    private Double area;
    private Integer countPeople;

    public CityDto(String name, Double area, Integer countPeople) {
        this.name = name;
        this.area = area;
        this.countPeople = countPeople;
    }

}
