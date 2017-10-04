package ru.rustem.exception;

public class CityIdNotUnique extends RuntimeException {

    public CityIdNotUnique(int cityId) {
        super("city not unique id ="+ cityId);
    }

}
