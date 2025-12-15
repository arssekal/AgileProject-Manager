package com.arssekal.AgileManager.exceptions;

public class SprintBacklogNotFoundException extends RuntimeException {
    public SprintBacklogNotFoundException(String s) {
        super(s);
    }
    public SprintBacklogNotFoundException(Long id) {
        super("sprint id: "+ id+ " is not found");
    }
}
