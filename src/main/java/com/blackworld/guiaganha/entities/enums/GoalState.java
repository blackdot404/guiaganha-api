package com.blackworld.guiaganha.entities.enums;

public enum GoalState {
    CREATED(1), IN_PROGRESS(2), PAUSED(3), COMPLETED(4);

    private int code;

    private GoalState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GoalState valueOf(int code) {
        for (GoalState value : GoalState.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid GoalState Code");
    }
}
