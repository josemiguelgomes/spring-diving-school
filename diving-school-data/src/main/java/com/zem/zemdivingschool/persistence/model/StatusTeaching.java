package com.zem.zemdivingschool.persistence.model;

public enum StatusTeaching {
    TEACHING("T"),
    NOT_TEACHING("N");

    private final String field;

    private StatusTeaching(String field) {
        this.field = field;
    }

    public String getStatusTeaching() {
        return field;
    }
}
