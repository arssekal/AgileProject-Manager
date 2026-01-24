package com.ensa.AgileManager.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("task with id "+id+ " is not found");
    }
    public TaskNotFoundException(String s) {
        super(s);
    }
}
