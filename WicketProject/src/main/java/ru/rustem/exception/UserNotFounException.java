package ru.rustem.exception;


public class UserNotFounException extends RuntimeException {
    static String message;

    public UserNotFounException(String login) {
        super();
        this.message = "User = " + login + "not found ";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
