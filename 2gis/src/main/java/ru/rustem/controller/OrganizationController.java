package ru.rustem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustem.dto.OrganizationDto;
import ru.rustem.service.OrganizationService;

import java.util.LinkedList;
import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @GetMapping("/organization")
    public ResponseEntity<List<OrganizationDto>> getCitys(){
        List<OrganizationDto> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/organization/{param}")
    public ResponseEntity<List<OrganizationDto>> getCityById(@PathVariable("param") String param){
        List<OrganizationDto> result ;
        try {
            Integer id = Integer.parseInt(param);
            result = new LinkedList<>();
            result.add(service.find(id));
        } catch (NumberFormatException e) {
            result = service.find(param);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/organization/{id}")
    public ResponseEntity<OrganizationDto> addCity(@PathVariable("id") Integer id,@RequestBody OrganizationDto model){
        service.save(model, id);
        return ResponseEntity.ok(model);
    }

    @PostMapping("/organization")
    public ResponseEntity<List<OrganizationDto>> addCitys(@RequestBody List<OrganizationDto> list){
        service.saveBatch(list);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/organization/{id}")
    public void updateCity(@PathVariable("id") Integer id, @RequestBody OrganizationDto model){
        service.update(model, id);
    }
}
