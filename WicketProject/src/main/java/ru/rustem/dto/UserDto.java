package ru.rustem.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto implements Serializable {

    String login;
    String password;
    String cpassword;

    public UserDto(String login, String password, String cpassword) {
        this.login = login;
        this.password = password;
        this.cpassword = cpassword;
    }

}
