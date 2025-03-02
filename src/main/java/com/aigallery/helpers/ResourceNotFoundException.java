package com.aigallery.helpers;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);

    }
    public ResourceNotFoundException() {
        super("Resource Not Found!");
    }
// class created during user form building register....
}
