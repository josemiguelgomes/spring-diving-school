package com.zem.zemdivingschool.persistence.model;

public enum StatusTeaching {
    TEACHING("T"),
    NOT_TEACHING("N");

    private String description;

    private StatusTeaching(String statusTeaching) {
        this.description = statusTeaching;
    }

    public String getStatusTeaching() {
        return description;
    }
}
