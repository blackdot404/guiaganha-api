package com.blackworld.guiaganha.services.exceptions;

public class FinancialGoalNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FinancialGoalNotFoundException(Object id) {
        super("Financial goal not found. Id " + id);
    }

}
