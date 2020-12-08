package com.ness.fizzbuzz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    private static final String ERROR = "error";


/**
     * It catches all the Business exception
     *
     * @param e of type {@link BusinessException}
     * @return a {@link ResponseEntity} with errors
     */

    @ExceptionHandler({ NumberFormatException.class })
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
        Map<String, String> response = Collections.singletonMap(ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}

