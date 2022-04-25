package com.zem.diveschool.persistence.model;

public enum SlotStatus {
    ON("1"),
    OFF("0");

    private final String field;

    private SlotStatus(String field) {
        this.field = field;
    }

    public String getSlotStatus() {
        return field;
    }
}
