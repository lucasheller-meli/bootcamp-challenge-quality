package com.desafioteste.desafioquality.exception;

public class ApiError {
    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    String message;
}
