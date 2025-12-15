package com.arssekal.AgileManager.exceptions;

public class EpicNotFoundException extends RuntimeException {
    public EpicNotFoundException(String s) {
        super(s);
    }
    public EpicNotFoundException(Long id) {
        super("epic with id: "+id+" is not found");
    }
}
