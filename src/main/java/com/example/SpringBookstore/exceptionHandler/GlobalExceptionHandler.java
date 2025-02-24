package com.example.SpringBookstore.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleBadRequestException(RuntimeException runtimeException) {
        ErrorDetail error = new ErrorDetail(BAD_REQUEST.value(), "BAD REQUEST", runtimeException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
