package com.blackworld.guiaganha.services.exceptions;

public class FixedNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FixedNotFoundException(Object id) {
        super("Fixed cost not found. Id " + id);
    }

}
