package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.rustem.converter.DtoToOrganizationConverter;
import ru.rustem.converter.OrganizationToDtoConverter;
import ru.rustem.dao.OrganizathionDao;
import ru.rustem.dto.OrganizationDto;
import ru.rustem.exception.OrganizationNotFoundException;
import ru.rustem.exception.OrganizationNotUnidueException;
import ru.rustem.model.Organization;
import ru.rustem.model.Street;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizathionDao dao;

    private DtoToOrganizationConverter converter = new DtoToOrganizationConverter();
    private OrganizationToDtoConverter converterToDto = new OrganizationToDtoConverter();

    @Override
    public Integer save(OrganizationDto model, int id) {
        try {
            Organization organization = converter.convert(model);
            return dao.save(organization);
        } catch (DuplicateKeyException e) {
            throw  new OrganizationNotUnidueException(id);
        }
    }

    @Override
    public OrganizationDto find(Integer id) {
        try {
            return converterToDto.convert(dao.find(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new OrganizationNotFoundException(id);
        }
    }

    @Override
    public void update(OrganizationDto model, int id) {
        Organization organization = converter.convert(model);
        dao.update(organization);
    }

    @Override
    public List<OrganizationDto> findAll() {
        List<OrganizationDto> cityDtoList = dao.findAll().stream()
                .map(x->converterToDto.convert(x))
                .collect(Collectors.toList());
        return cityDtoList;
    }

    @Override
    public int[] saveBatch(List<OrganizationDto> list) {
        List<Organization> cities = list.stream().map(x->converter.convert(x)).collect(Collectors.toList());
        return dao.saveBatch(cities);
    }

    @Override
    public List<OrganizationDto> find(String name) {
        try {
            List<OrganizationDto> cityDtoList = dao.find(name).stream()
                    .map(x->converterToDto.convert(x))
                    .collect(Collectors.toList());
            return cityDtoList;
        } catch (EmptyResultDataAccessException ex) {
            throw new OrganizationNotFoundException(name);
        }
    }
}
