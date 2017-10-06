package ru.rustem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.CityDto;
import ru.rustem.model.City;


public class CityToDtoConverter implements Converter<City, CityDto> {
    @Override
    public CityDto convert(City city) {
        return new CityDto(city.getName(),
                city.getArea(),
                city.getCountPeople());
    }
}
