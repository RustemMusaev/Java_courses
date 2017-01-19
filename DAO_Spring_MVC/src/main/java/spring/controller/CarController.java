package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.models.Car;
import spring.models.User;
import spring.service.CarService;
import spring.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    //showCars
    @GetMapping(value = "/users/{id}/Car")
    @ResponseBody
    ModelAndView showCars(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Car> carList = userService.find(id).getMycars();
        modelAndView.addObject("cars", carList);
        modelAndView.addObject("userid", id);
        modelAndView.addObject("car", new Car());
        modelAndView.setViewName("showCars");
        return modelAndView;
    }
    //addCar
    @PostMapping(value = "/users/{id}/Car")
    @ResponseBody ModelAndView addCar(@ModelAttribute("car") Car car, @PathVariable("id") int userid) {
        ModelAndView modelAndView = new ModelAndView();
        car.setUser(userService.find(userid));
        carService.save(car);
        List<Car> carList = userService.find(userid).getMycars();
        modelAndView.addObject("cars", carList);
        modelAndView.setViewName("showCars");
        return modelAndView;
    }
    //deleteCar
    @DeleteMapping(value = "/users/{userid}/Car/{carid}")
    public String deleteCar(@PathVariable("userid") int userid,@PathVariable("carid") int carid) {
        carService.delete(carid);
        return "redirect:/users/"+userid+"/Car";
    }
    //updateCar(POST)
    @PostMapping(value = "/users/{userid}/Car/{carid}")
    @ResponseBody ModelAndView updateCar(@PathVariable("userid") int userid,@PathVariable("carid") int carid) {
        ModelAndView modelAndView = new ModelAndView();
        Car car =carService.find(carid);
        car.setUser(userService.find(userid));
        modelAndView.addObject("car", car);
        modelAndView.setViewName("CarUpdate");
        return modelAndView;
    }
    //updateCar(PUT)
    @PutMapping(value = "/users/{userid}/Car/{carid}")
    public String updateCar(@PathVariable("userid") int userid,@PathVariable("carid") int carid,
                             @RequestParam("model") String model,
                             @RequestParam("color") String color) {
        Car car=carService.find(carid);
        if (model.equals("")) {
        } else {
            car.setModel(model);
        }
        if (color.equals("")){
        } else {
            car.setColor(color);}
        car.setUser(userService.find(userid));
        carService.update(car);
        return "redirect:/users/"+userid+"/Car";
    }
}

