package com.zem.zemdivingschool.persistence.model;

public enum Level {
    EASY("E"),
    MEDIUM("M"),
    DIFFICULT("D");

    private String description;

    private Level(String level) {
        this.description = level;
    }

    public String getLevel() {
        return description;
    }
}
