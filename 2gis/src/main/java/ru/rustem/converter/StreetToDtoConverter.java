package ru.rustem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.StreetDto;
import ru.rustem.model.Street;

public class StreetToDtoConverter implements Converter<Street, StreetDto> {
    @Override
    public StreetDto convert(Street model) {
        return StreetDto.builder()
                .name(model.getName())
                .length(model.getLength())
                .build();}
}
