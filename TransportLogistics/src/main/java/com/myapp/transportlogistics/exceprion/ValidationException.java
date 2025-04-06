package com.myapp.transportlogistics.exceprion;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
