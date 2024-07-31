package com.nocountry.apiS16.advice;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleFieldErros(MethodArgumentNotValidException exceptions) {
        Map<String, String> errors = new HashMap<>();
        exceptions.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }
}
