package com.zem.zemdivingschool.persistence.model;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private String description;

    private Gender(String gender) {
        this.description = gender;
    }

    public String getGender() {
        return description;
    }
}
