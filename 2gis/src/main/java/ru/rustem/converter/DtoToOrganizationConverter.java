package ru.rustem.converter;


import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.OrganizationDto;
import ru.rustem.dto.StreetDto;
import ru.rustem.model.Organization;
import ru.rustem.model.Street;

public class DtoToOrganizationConverter implements Converter<OrganizationDto, Organization> {

    @Override
    public Organization convert(OrganizationDto model) {
        return Organization.builder()
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
