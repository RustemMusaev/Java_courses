package ru.rustem.converter;


import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.OrganizationDto;
import ru.rustem.dto.StreetDto;
import ru.rustem.model.Organization;
import ru.rustem.model.Street;

public class DtoToOrganizationConverter implements Converter<OrganizationDto, Organization> {

    @Override
    public Organization convert(OrganizationDto model) {
        return new Organization(model.getName(),
                model.getCity(),
                model.getStreet(),
                model.getHouseNumber(),
                model.getDescription(),
                model.getWebsite(),
                model.getDateUpdate());
    }
}
