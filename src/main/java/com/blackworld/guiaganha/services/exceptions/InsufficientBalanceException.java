package com.blackworld.guiaganha.services.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException(Object id) {
        super("Insufficient balance for this goal. Id " + id);
    }

}
