package ru.rustem.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Street {
    public Street(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    private Integer id;
    private String name;
    private Integer length;

    public Street(Integer id, String name, Integer length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }
}

