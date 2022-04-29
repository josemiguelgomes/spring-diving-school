package com.zem.diveschool.persistence.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Slot> slots = new HashSet<>();

    //
    // Constructors
    //

    public Course() {
        super();
    }

    public Course(Long id, String name, Level level, Set<Slot> slots) {
        super(id);
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


    //
    //
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId())
                && Objects.equals(name, course.name)
                && level == course.level
                && Objects.equals(slots, course.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, level);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
