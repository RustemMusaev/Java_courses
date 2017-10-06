package ru.rustem.exception;


public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(int id) {
        super("city not found id ="+ id);
    }
}
