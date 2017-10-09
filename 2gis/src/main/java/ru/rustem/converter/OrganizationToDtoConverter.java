package ru.rustem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.OrganizationDto;
import ru.rustem.model.Organization;


public class OrganizationToDtoConverter implements Converter<Organization, OrganizationDto> {

    @Override
    public OrganizationDto convert(Organization model) {
        return OrganizationDto.builder()
                .name(model.getName())
                .city(model.getCity())
                .street(model.getStreet())
                .houseNumber(model.getHouseNumber())
                .description(model.getDescription())
                .website(model.getWebsite())
                .dateUpdate(model.getDateUpdate())
                .build();
    }
}
