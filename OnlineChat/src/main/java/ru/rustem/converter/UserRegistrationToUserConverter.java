package ru.rustem.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rustem.dto.UserRegistration;
import ru.rustem.model.User;

/**
 * Convert data from registration FORM(does not contain ID) to user. The password is encrypted.
 */
public class UserRegistrationToUserConverter {
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User userRegistrationToUserConverter(UserRegistration userRegistration) {
        User user = new User();
        user.setLogin(userRegistration.getLogin());
        user.setPasswordHash(passwordEncoder.encode(userRegistration.getPassword()));
        user.setName(userRegistration.getName());
        user.setSurname(userRegistration.getSurname());
        user.setPhone(userRegistration.getPhone());
        user.setEmail(userRegistration.getEmail());
        return user;
    }
}
