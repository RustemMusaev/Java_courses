package ru.rustem.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Phone {

    private Integer id;
    private String number;
    private Organization organization;

    public Phone(Integer id, String number, Organization organization) {
        this.id = id;
        this.number = number;
        this.organization = organization;
    }
}
