package ru.rustem.converter;


import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.StreetDto;
import ru.rustem.model.Street;

public class DtoToStreetConverter implements Converter<StreetDto, Street> {
    @Override
    public Street convert(StreetDto model) {
        return Street.builder()
                .name(model.getName())
                .length(model.getLength())
                .build();
    }
}
