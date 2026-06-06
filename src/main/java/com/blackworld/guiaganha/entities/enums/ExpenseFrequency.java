package com.blackworld.guiaganha.entities.enums;

public enum ExpenseFrequency {
    DAILY(1), MONTHLY(2), YEARLY(3);

    private int code;

    private ExpenseFrequency(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ExpenseFrequency valueOf(int code) {
        for (ExpenseFrequency value : ExpenseFrequency.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid ExpenseFrequency Code");
    }
}
