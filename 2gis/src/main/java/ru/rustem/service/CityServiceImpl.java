package ru.rustem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.rustem.converter.UserToUserDtoConverter;
import ru.rustem.dao.CityDao;
import ru.rustem.dto.CityDto;
import ru.rustem.exception.CityIdNotUnique;
import ru.rustem.exception.CityNotFound;
import ru.rustem.model.City;

import java.util.List;
import java.util.stream.Collectors;

import static ru.rustem.converter.UserToUserDtoConverter.userToUserDtoConverter;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;


    @Override
    public Integer save(CityDto model, int id) {
        if (find(id) !=null){
            throw new CityIdNotUnique(id);
        } else {
            City city = new City(id,model.getName(), model.getArea(), model.getCountPeople());
            return cityDao.save(city);
        }
    }

    @Override
    public CityDto find(Integer id) {
        try {
            return userToUserDtoConverter(cityDao.find(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new CityNotFound(id);
        }
    }

    @Override
    public void update(CityDto model) {

    }

    @Override
    public List<CityDto> findAll() {
        List<CityDto> cityDtoList = cityDao.findAll().stream()
                .map(UserToUserDtoConverter::userToUserDtoConverter).collect(Collectors.toList());
        return cityDtoList;
    }

    @Override
    public int[] saveBatch(List<CityDto> list) {
        return new int[0];
    }
}
