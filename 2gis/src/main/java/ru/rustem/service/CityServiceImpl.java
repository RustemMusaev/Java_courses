package ru.rustem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.rustem.converter.DtoToCityConverter;
import ru.rustem.converter.CityToDtoConverter;
import ru.rustem.dao.CityDao;
import ru.rustem.dto.CityDto;
import ru.rustem.exception.IdNotUniqueException;
import ru.rustem.exception.NotFoundException;
import ru.rustem.model.City;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao dao;

    @Override
    public Integer save(CityDto model, int id) {
        try {
            City city = new City(id,model.getName(), model.getArea(), model.getCountPeople());
            return dao.save(city);
        } catch (DuplicateKeyException e) {
            throw  new IdNotUniqueException(model.getName(),id);
        }
    }

    @Override
    public CityDto find(Integer id) {
        try {
            return new CityToDtoConverter().convert(dao.find(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("City",id);
        }
    }

    @Override
    public void update(CityDto model, int id) {
        City city = new City(id,model.getName(), model.getArea(), model.getCountPeople());
        dao.update(city);
    }

    @Override
    public List<CityDto> findAll() {
        List<CityDto> cityDtoList = dao.findAll().stream()
                .map(x->new CityToDtoConverter().convert(x)).collect(Collectors.toList());
        return cityDtoList;
    }

    @Override
    public int[] saveBatch(List<CityDto> list) {
            List<City> cities = list.stream().map(x->new DtoToCityConverter().convert(x)).collect(Collectors.toList());
            return dao.saveBatch(cities);
    }
}
