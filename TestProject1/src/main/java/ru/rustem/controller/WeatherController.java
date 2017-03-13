package ru.rustem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WeatherController {

    @GetMapping(value = "/")
    ModelAndView showNews() {
        ModelAndView modelAndView = new ModelAndView("weather");
       // modelAndView.addObject("listPageCount", listPageCount);
        return modelAndView;
    }
}
