package com.zem.zemdivingschool.persistence.model;

public enum StatusTeachingCourse {
    TEACHING("T"),
    NOT_TEACHING("N");

    private String description;

    private StatusTeachingCourse(String statusTeachingCourse) {
        this.description = statusTeachingCourse;
    }

    public String getStatusTeachingCourse() {
        return description;
    }
}
