package ru.rustem.service;

import ru.rustem.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService extends BaseService<OrganizationDto> {
    List<OrganizationDto> find(String name);
}
