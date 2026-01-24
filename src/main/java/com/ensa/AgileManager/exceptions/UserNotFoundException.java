package com.ensa.AgileManager.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with id "+ userId + " is not found");
    }
    public UserNotFoundException(String s) {
        super(s);
    }
}
