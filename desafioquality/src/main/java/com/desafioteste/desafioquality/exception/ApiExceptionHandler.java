package com.desafioteste.desafioquality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BairroNaoExisteException.class)
    public ResponseEntity<ApiError> handleBairroNaoExisteException(BairroNaoExisteException bairroNaoExisteException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(bairroNaoExisteException.getMessage()));
    }


}
