package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustem.dto.CityDto;
import ru.rustem.service.CityService;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> getCitys(){
        List<CityDto> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable("id") Integer id){
        CityDto result = service.find(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/city/{id}")
    public ResponseEntity<CityDto> addCity(@PathVariable("id") Integer id,@RequestBody CityDto cityDto){
        service.save(cityDto, id);
        return ResponseEntity.ok(cityDto);
    }

    @PostMapping("/city")
    public ResponseEntity<List<CityDto>> addCitys(@RequestBody List<CityDto> cityDtoList){
        service.saveBatch(cityDtoList);
        return ResponseEntity.ok(cityDtoList);
    }

    @PutMapping("/city/{id}")
    public void updateCity(@PathVariable("id") Integer id, @RequestBody CityDto cityDto){
            service.update(cityDto, id);
    }
}
