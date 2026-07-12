package com.blackworld.guiaganha.services.exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExpenseNotFoundException(Object id) {
        super("Variable expense not found. Id " + id);
    }

}
