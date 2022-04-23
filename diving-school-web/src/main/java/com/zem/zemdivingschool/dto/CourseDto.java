package com.zem.zemdivingschool.dto;

import com.zem.zemdivingschool.persistence.model.Level;

public class CourseDto {
    private String name;
    private Level level;

    public CourseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
