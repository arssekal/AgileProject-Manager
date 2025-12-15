package com.arssekal.AgileManager.exceptions;

public class UserStoryNotFoundException extends RuntimeException {
    public UserStoryNotFoundException(Long id) {
        super("user story with id "+ id + " is not found");
    }
    public UserStoryNotFoundException(String s) {
        super(s);
    }
}
