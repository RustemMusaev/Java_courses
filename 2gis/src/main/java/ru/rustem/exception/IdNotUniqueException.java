package ru.rustem.exception;

public class IdNotUniqueException extends RuntimeException {
    String message;
    public IdNotUniqueException(String name, int id) {
        super();
        this.message =name+" not unique id =" + id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
