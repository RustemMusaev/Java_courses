package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.rustem.models.Weather;
import ru.rustem.service.WeatherServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class WeatherController {

    @Autowired
    WeatherServiceImpl weatherService;

    @GetMapping(value = "/")
    ModelAndView showWeather(HttpServletRequest servletRequest) {
        ModelAndView modelAndView = new ModelAndView("weather");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        Weather novosibirsk = weatherService.getWeather(1496747);
        Weather moscow = weatherService.getWeather(524901);
        Weather stPetersburg = weatherService.getWeather(498817);
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        String getEUR = weatherService.getEur();
        String getUSD = weatherService.getUsd();

        modelAndView.addObject("novosibirsk", novosibirsk);
        modelAndView.addObject("moscow", moscow);
        modelAndView.addObject("stPetersburg", stPetersburg);
        modelAndView.addObject("ipAddress", ipAddress);
        modelAndView.addObject("dateFormat", dateFormat);
        modelAndView.addObject("getEUR", getEUR);
        modelAndView.addObject("getUSD", getUSD);
        return modelAndView;
    }
}
