package com.zem.zemdivingschool.persistence.model;

public enum SlotStatus {
    ON("1"),
    OFF("0");

    private String description;

    private SlotStatus(String slotStatus) {
        this.description = slotStatus;
    }

    public String getSlotStatus() {
        return description;
    }
}
