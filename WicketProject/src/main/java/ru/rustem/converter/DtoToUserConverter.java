package ru.rustem.converter;


import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.UserDto;
import ru.rustem.model.User;

public class DtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto model) {
        return new User(model.getLogin(), model.getPassword(), "USER");
    }
}
