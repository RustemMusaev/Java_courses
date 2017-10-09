package ru.rustem.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class ExceptionDto implements Serializable{

    private String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

}
