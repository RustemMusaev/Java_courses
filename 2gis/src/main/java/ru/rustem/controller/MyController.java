package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustem.dao.CityDao;
import ru.rustem.dto.CityDto;
import ru.rustem.exception.CityIdNotUnique;
import ru.rustem.model.City;
import ru.rustem.service.CityService;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityDao cityDao;

    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> getCitys(){
        List<CityDto> result = cityService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Integer id){
        City result = cityDao.find(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/city/{id}")
    @ExceptionHandler(CityIdNotUnique.class)
    public ResponseEntity<CityDto> addCity(@PathVariable("id") Integer id,@RequestBody CityDto cityDto){
        cityService.save(cityDto, id);
        return ResponseEntity.ok(cityDto);
    }

    @PutMapping("/city/{id}")
    public void updateCity(@PathVariable("id") Integer id, @RequestBody CityDto cityDto){
        if (cityDao.find(id) != null) {
            City city = new City(id,cityDto.getName(), cityDto.getArea(), cityDto.getCountPeople());
            cityDao.update(city);
        }
    }
}
