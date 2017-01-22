package spring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.converter.CarToCarDtoConverter;
import spring.converter.UserToUserDtoConverter;
import spring.dto.CarDto;
import spring.dto.UserDto;
import spring.models.Car;
import spring.models.User;
import spring.service.CarService;
import spring.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestCarController {
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;

    @GetMapping(value = "/users/{id}/Car")
    public List<CarDto> showCar(@PathVariable("id") Integer id){
        List<Car>  carList=userService.find(id).getMycars();
        List<CarDto> result=new ArrayList<CarDto>();
        for (Car car :carList){
            result.add(CarToCarDtoConverter.convertWithoutUser(car));
        }
        return result;
    }
}
