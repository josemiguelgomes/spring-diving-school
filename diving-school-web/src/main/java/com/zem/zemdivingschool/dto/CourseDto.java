package com.zem.zemdivingschool.dto;

import com.zem.zemdivingschool.persistence.model.Level;

public class CourseDto {
    private Long id;
    private String name;
    private Level level;

    //
    // Constructor
    //
    public CourseDto() {
    }

    //
    // Getters & Setters
    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
