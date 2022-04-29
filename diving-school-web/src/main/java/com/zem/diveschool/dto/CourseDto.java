package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Level;
import com.zem.diveschool.persistence.model.Slot;

import java.util.HashSet;
import java.util.Set;


public class CourseDto extends GenericDto<CourseDto> {
    private Long id;
    private String name;
    private Level level;

    private Set<Slot> slots = new HashSet<>();

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

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }
}
