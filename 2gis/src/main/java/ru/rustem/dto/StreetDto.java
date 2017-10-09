package ru.rustem.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class StreetDto implements Serializable {
    private String name;
    private Integer length;

    public StreetDto(String name, Integer length) {
        this.name = name;
        this.length = length;
    }
}
