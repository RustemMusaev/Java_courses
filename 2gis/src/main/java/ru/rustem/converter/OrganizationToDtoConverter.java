package ru.rustem.converter;

import org.springframework.core.convert.converter.Converter;
import ru.rustem.dto.OrganizationDto;
import ru.rustem.model.Organization;


public class OrganizationToDtoConverter implements Converter<Organization, OrganizationDto> {

    @Override
    public OrganizationDto convert(Organization model) {
        return new OrganizationDto(model.getName(),
                model.getCity(),
                model.getStreet(),
                model.getHouseNumber(),
                model.getDescription(),
                model.getWebsite(),
                model.getDateUpdate());
    }
}
