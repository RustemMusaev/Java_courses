package ru.rustem.exception;


public class CityNotFound extends RuntimeException {
    public CityNotFound(int cityId) {
        super("city not found id ="+ cityId);
    }
}
