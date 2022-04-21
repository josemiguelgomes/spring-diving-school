package com.zem.zemdivingschool.persistence.model;

// ISO 639.2
public enum Language {
    PORTUGUESE("por"),
    FRENCH("fre"),
    ENGLISH("eng"),
    SPANISH("spa"),
    UKRANIAN("ukr");

    private String description;

    private Language(String language) {
        this.description = language;
    }

    public String getLanguage() {
        return description;
    }
}
