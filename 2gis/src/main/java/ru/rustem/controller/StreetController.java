package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustem.dto.StreetDto;
import ru.rustem.service.StreetService;

import java.util.List;

@RestController
public class StreetController {

    @Autowired
    private StreetService service;

    @GetMapping("/street")
    public ResponseEntity<List<StreetDto>> getCitys(){
        List<StreetDto> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/street/{id}")
    public ResponseEntity<StreetDto> getCityById(@PathVariable("id") Integer id){
        StreetDto result = service.find(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/street/{id}")
    public ResponseEntity<StreetDto> addCity(@PathVariable("id") Integer id,@RequestBody StreetDto streetDto){
        service.save(streetDto, id);
        return ResponseEntity.ok(streetDto);
    }

    @PostMapping("/street")
    public ResponseEntity<List<StreetDto>> addCitys(@RequestBody List<StreetDto> streetDtoList){
        service.saveBatch(streetDtoList);
        return ResponseEntity.ok(streetDtoList);
    }

    @PutMapping("/street/{id}")
    public void updateCity(@PathVariable("id") Integer id, @RequestBody StreetDto streetDto){
        service.update(streetDto, id);
    }
}
