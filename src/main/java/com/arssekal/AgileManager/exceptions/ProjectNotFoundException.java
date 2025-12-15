package com.arssekal.AgileManager.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String s) {
        super(s);
    }
    public ProjectNotFoundException(Long id) {
        super("Project not found with id: " + id);
    }
}
