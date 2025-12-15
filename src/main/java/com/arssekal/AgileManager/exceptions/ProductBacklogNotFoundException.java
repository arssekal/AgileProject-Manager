package com.arssekal.AgileManager.exceptions;

public class ProductBacklogNotFoundException extends RuntimeException {
    public ProductBacklogNotFoundException(String s) {
        super(s);
    }
    public ProductBacklogNotFoundException(Long id) {
        super("product backlog with this id: "+ id +" isn't found");
    }
}
