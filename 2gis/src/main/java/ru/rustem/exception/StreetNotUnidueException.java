package ru.rustem.exception;

public class StreetNotUnidueException extends RuntimeException {
    public StreetNotUnidueException(int streeId) {
        super("city not unique id ="+ streeId);
    }
}
