package com.gai_app.gai_cars.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String description) {
        super(description);
    }
}
