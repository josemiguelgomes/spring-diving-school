package com.zem.zemdivingschool.persistence.model;

// ISO 639.2
public enum Language {
    PORTUGUESE("por"),
    FRENCH("fre"),
    ENGLISH("eng"),
    SPANISH("spa"),
    UKRANIAN("ukr");

    private final String field;

    private Language(String field) {
        this.field = field;
    }

    public String getLanguage() {
        return field;
    }
}
