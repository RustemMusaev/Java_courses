package ru.rustem.converter;

import ru.rustem.dto.CityDto;
import ru.rustem.model.City;


public class UserToUserDtoConverter {
    public static CityDto userToUserDtoConverter(City city) {
        return new CityDto(city.getName(), city.getArea(), city.getCountPeople());
    }
}
