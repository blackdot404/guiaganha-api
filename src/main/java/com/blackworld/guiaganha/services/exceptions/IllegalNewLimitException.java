package com.blackworld.guiaganha.services.exceptions;

import java.math.BigDecimal;

public class IllegalNewLimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalNewLimitException(BigDecimal limit) {
        super("Enter a valid limit. " + limit);
    }

}
