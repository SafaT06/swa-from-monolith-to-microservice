package edu.demo.monolith.web.exceptionmapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidCredentialsMapper {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleInvalidCredentialsException(RuntimeException ex) {
        return ErrorResponse.builder(ex, HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()), "Invalid credentials").build();
    }
}

