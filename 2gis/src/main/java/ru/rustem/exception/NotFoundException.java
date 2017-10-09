package ru.rustem.exception;


public class NotFoundException extends RuntimeException {
    String message;
    public NotFoundException(String name, int id) {
        super();
        this.message = name+" not found id =" + id;
    }
    public NotFoundException(String name) {
        super();
        this.message = name+" not found ";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
