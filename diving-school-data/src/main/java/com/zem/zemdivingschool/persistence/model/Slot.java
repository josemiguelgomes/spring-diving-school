package com.zem.zemdivingschool.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Slot {
    @Id
    Long id;

    @Column(name = "title")
    String title;
    @Column(name = "starting_date")
    Date startingDate;
    @Column(name = "ending_date")
    Date endingDate;
    // TODO: create the relationship to location
    @Transient
    Location location;
    @Enumerated(EnumType.STRING)
    SlotStatus status;
    // TODO: create the relationship to course
    @Transient
    Course course;
    // TODO: create the relationship to languages
    @Transient
    List<Language> languages = new ArrayList<>();
    // TODO: create the relationship to students
    @Transient
    List<Student> students = new ArrayList<>();
    // TODO: create the relationship to instructors
    @Transient
    List<Instructor> instructors = new ArrayList<>();

    //
    // Constructors
    //

    public Slot() {
    }

    public Slot(String title, Date startingDate, Date endingDate, Location location, SlotStatus status, Course course,
                List<Language> languages, List<Student> students, List<Instructor> instructors) {
        this.title = title;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.location = location;
        this.status = status;
        this.course = course;
        this.languages = languages;
        this.students = students;
        this.instructors = instructors;
    }

    //
    // Methods
    //
    public void addLanguage(Language language) {
        this.languages.add(language);
    }
    public void deleteLanguage(Language language) { this.languages.remove(language); }

    public void addStudent(Student student) {
        this.students.add(student);
    }
    public void deleteStudent(Student student) { this.students.remove(student); }

    public void addInstructor(Instructor instructor) {
        this.instructors.add(instructor);
    }
    public void deleteInstructor(Instructor instructor) { this.instructors.remove(instructor); }

    //
    // Setters & Getters
    //

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }


    //
    //
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(title, slot.title)
            && Objects.equals(startingDate, slot.startingDate)
            && Objects.equals(endingDate, slot.endingDate)
            && Objects.equals(location, slot.location)
            && status == slot.status
            && Objects.equals(course, slot.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startingDate, endingDate, location, status, course);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "title='" + title + '\'' +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", location=" + location +
                ", status=" + status +
                ", course=" + course +
                ", languages=" + languages +
                ", students=" + students +
                ", instructors=" + instructors +
                '}';
    }

}

