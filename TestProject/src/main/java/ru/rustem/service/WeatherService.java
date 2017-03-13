package ru.rustem.service;


import ru.rustem.models.Weather;

public interface WeatherService {
    Weather getWeather(Integer idCity);
    String getUsd();
    String getEur();
}
