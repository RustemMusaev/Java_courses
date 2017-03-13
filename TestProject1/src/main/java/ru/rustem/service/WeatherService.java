package ru.rustem.service;


import ru.rustem.models.Weather;

public interface WeatherService {
    public Weather getWeather(Integer idCity);
}
