package com.app.JuberEats.exceptions;

import com.app.JuberEats.primitives.AppError;
import com.app.JuberEats.primitives.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> validationErrors = new HashMap();

        ex.getBindingResult().getAllErrors()
                .forEach(err -> {
                    String fieldName = ((FieldError)err).getField();
                    String message = err.getDefaultMessage();

                    validationErrors.put(fieldName, message);
                });

        AppError error = new AppError(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "One or more validation errors",
                validationErrors);

        Result result = new Result(error);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
