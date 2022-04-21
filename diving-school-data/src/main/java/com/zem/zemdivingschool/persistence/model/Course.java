package com.zem.zemdivingschool.persistence.model;

import java.util.Objects;

public class Course {
    String name;
    Level level;

    //
    // Constructors
    //

    public Course() {
    }

    public Course(String name, Level level) {
        this.name = name;
        this.level = level;
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


    //
    //
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) && level == course.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
