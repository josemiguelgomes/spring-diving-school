package com.zem.zemdivingschool.persistence.model;

public enum Country {
    PORTUGAL("PRT"),
    FRANCE("FRA"),
    UNITED_KINGDOM("GBR"),
    SPAIN("ESP"),
    UKRAINE("UKR");

    private String description;

    private Country(String country) {
        this.description = country;
    }

    public String getCountry() {
        return description;
    }
}
