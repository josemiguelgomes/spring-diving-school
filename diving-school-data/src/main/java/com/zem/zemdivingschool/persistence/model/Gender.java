package com.zem.zemdivingschool.persistence.model;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private final String field;

    private Gender(String field) {
        this.field = field;
    }

    public String getGender() {
        return field;
    }
}
