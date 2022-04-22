package com.zem.zemdivingschool.persistence.model;

public enum StatusTeachingCourse {
    TEACHING("T"),
    NOT_TEACHING("N");

    private final String field;

    private StatusTeachingCourse(String field) {
        this.field = field;
    }

    public String getStatusTeachingCourse() {
        return field;
    }
}
