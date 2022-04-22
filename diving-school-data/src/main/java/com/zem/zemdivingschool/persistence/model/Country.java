package com.zem.zemdivingschool.persistence.model;

public enum Country {
    PORTUGAL("PRT"),
    FRANCE("FRA"),
    UNITED_KINGDOM("GBR"),
    SPAIN("ESP"),
    UKRAINE("UKR");

    private final String field;

    private Country(String field) {
        this.field = field;
    }

    public String getCountry() {
        return field;
    }
}
