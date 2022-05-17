package com.zem.diveschool.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "slots")
public class Slot extends BaseEntity<Slot> {
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

    @Singular
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
    @Builder
    public Slot(Long id, String title, Date startDate, Date endDate, Location location, SlotStatus status,
                Course course,
                Set<SlotLanguage> languages,
                Set<Student> students,
                Set<Instructor> instructors) {
        super(id);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
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
    public void add(SlotLanguage language) {
        this.languages.add(language);
    }
    public void delete(SlotLanguage language) { this.languages.remove(language); }

    public void add(Student student) { this.students.add(student); }
    public void delete(Student student) { this.students.remove(student); }

    public void add(Instructor instructor) {
        this.instructors.add(instructor);
    }
    public void delete(Instructor instructor) { this.instructors.remove(instructor); }
}

