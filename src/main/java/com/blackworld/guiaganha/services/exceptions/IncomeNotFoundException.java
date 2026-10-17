package com.blackworld.guiaganha.services.exceptions;

public class IncomeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IncomeNotFoundException(Object id) {
        super("Income not found. Id " + id);
    }

}
