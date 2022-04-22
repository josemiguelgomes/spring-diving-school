package com.zem.zemdivingschool.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Slot> slots = new ArrayList<>();

    //
    // Constructors
    //

    public Course() {
        super();
    }

    public Course(Long id, String name, Level level, List<Slot> slots) {
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
