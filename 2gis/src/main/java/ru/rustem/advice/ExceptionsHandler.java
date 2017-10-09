package ru.rustem.advice;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.rustem.dto.ExceptionDto;
import ru.rustem.exception.IdNotUniqueException;
import ru.rustem.exception.NotFoundException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionDto NotFoundException(NotFoundException exception){
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(IdNotUniqueException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionDto IdNotUniqueException(IdNotUniqueException exception){
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ExceptionDto serverError(CannotGetJdbcConnectionException exception){
        return new ExceptionDto(exception.getMessage());
    }
}
