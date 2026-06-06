package com.blackworld.guiaganha.entities.enums;

public enum IncomeCategory {
    FREELANCE(1), MAINTENANCE(2), DELIVERIES(3), OTHERS(4);

    private int code;

    private IncomeCategory(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static IncomeCategory valueOf(int code) {
        for (IncomeCategory value : IncomeCategory.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid IncomeCategory Code");
    }
}
