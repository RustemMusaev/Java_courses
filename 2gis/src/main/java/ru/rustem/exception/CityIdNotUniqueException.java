package ru.rustem.exception;

public class CityIdNotUniqueException extends RuntimeException {
    public CityIdNotUniqueException(int id) {
        super("city not unique id ="+ id);
    }

}
