package com.example.SpringBookstore.exceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException entityNotFoundException) {
        ErrorDetail error = new ErrorDetail(NOT_FOUND.value(), "NOT FOUND", entityNotFoundException.getMessage());
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity<?> handleInputMismatchException(InputMismatchException inputMismatchException) {
        ErrorDetail error = new ErrorDetail(BAD_REQUEST.value(), "INPUT MISMATCH", inputMismatchException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String property = getProperty(error);
            String message = error.getDefaultMessage();
            errors.put(property, message);
        });

        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

    private String getProperty(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return ((FieldError) objectError).getField();
        }

        return objectError.getObjectName();
    }
}
