package ru.rustem.exception;


public class StreetNotFoundException extends RuntimeException {
    public StreetNotFoundException(int id) {
        super("city not found id ="+ id);
    }
}
