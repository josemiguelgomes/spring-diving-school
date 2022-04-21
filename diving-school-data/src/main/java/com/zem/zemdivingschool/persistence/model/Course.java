package com.zem.zemdivingschool.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    Long id;

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    Level level;

    // TODO: create the relationship to slots
    @Transient
    private List<Slot> slots = new ArrayList<>();

    //
    // Constructors
    //

    public Course() {
    }

    public Course(Long id, String name, Level level, List<Slot> slots) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.slots = slots;
    }

//
    // Methods
    //


    //
    // Setters & Getters
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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }


    //
    //
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id)
                && Objects.equals(name, course.name) && level == course.level && Objects.equals(slots, course.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, slots);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", slots=" + slots +
                '}';
    }
}
