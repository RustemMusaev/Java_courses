package ru.rustem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.CityDto;
import ru.rustem.model.City;

public class DtoToCityConverter implements Converter<CityDto, City> {
    @Override
    public City convert(CityDto model) {
        return City.builder()
                .name(model.getName())
                .area(model.getArea())
                .countPeople(model.getCountPeople())
                .build();
    }
}
