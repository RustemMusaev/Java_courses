package ru.rustem.service;


import ru.rustem.model.Weather;

public interface WeatherService {
    Weather getWeather(Integer idCity);
    String getUsd();
    String getEur();
    void saveCount(Integer count);
    Integer findCount();
}
