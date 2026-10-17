package com.blackworld.guiaganha.services.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsException(Object email) {
        super("Email already Exists " + email);
    }

}
