package com.zem.diveschool.persistence.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "slots")
public class Slot extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @OneToMany(mappedBy = "slot", cascade = { CascadeType.ALL })
    private Set<SlotLanguage> languages = new HashSet<>();
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Slot_Student",
            joinColumns = { @JoinColumn(name = "slot_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<Student> students = new HashSet<>();
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Slot_Instructor",
            joinColumns = { @JoinColumn(name = "slot_id") },
            inverseJoinColumns = { @JoinColumn(name = "instructor_id") }
    )
    private Set<Instructor> instructors = new HashSet<>();

    //
    // Constructors
    //

    public Slot() {
        super();
    }

    public Slot(Long id, String title, Date startingDate, Date endingDate, Location location, SlotStatus status,
                Course course, Set<SlotLanguage> languages, Set<Student> students, Set<Instructor> instructors) {
        super(id);
        this.title = title;
        this.startDate = startingDate;
        this.endDate = endingDate;
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
    public void addLanguage(SlotLanguage language) {
        this.languages.add(language);
    }
    public void deleteLanguage(SlotLanguage language) { this.languages.remove(language); }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Set<SlotLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<SlotLanguage> languages) {
        this.languages = languages;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
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
            && Objects.equals(startDate, slot.startDate)
            && Objects.equals(endDate, slot.endDate)
            && Objects.equals(location, slot.location)
            && status == slot.status
            && Objects.equals(course, slot.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, location, status, course);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "title='" + title + '\'' +
                ", startingDate=" + startDate +
                ", endingDate=" + endDate +
                ", location=" + location +
                ", status=" + status +
                ", course=" + course +
                ", languages=" + languages +
                ", students=" + students +
                ", instructors=" + instructors +
                '}';
    }

}

