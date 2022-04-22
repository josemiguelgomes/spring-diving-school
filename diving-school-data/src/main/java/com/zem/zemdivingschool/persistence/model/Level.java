package com.zem.zemdivingschool.persistence.model;

public enum Level {
    EASY("E"),
    MEDIUM("M"),
    DIFFICULT("D");

    private final String field;

    private Level(String field) {
        this.field = field;
    }

    public String getLevel() {
        return field;
    }
}
