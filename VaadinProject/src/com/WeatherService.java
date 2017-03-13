package com;


public interface WeatherService {
    Weather getWeather(Integer idCity);
    String getUsd();
    String getEur();
}
