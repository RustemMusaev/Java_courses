package ru.rustem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.CityDto;
import ru.rustem.model.City;


public class CityToDtoConverter implements Converter<City, CityDto> {
    @Override
    public CityDto convert(City model) {
        return CityDto.builder()
                .name(model.getName())
                .area(model.getArea())
                .countPeople(model.getCountPeople())
                .build();
    }
}
