package com.zem.zemdivingschool.persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Card {
    @Id
    private Long id;
    @Column(name = "course")
    String course;
    @Column(name = "student_name")
    String studentName;
    @Column(name = "start_date")
    Date startDate ;
    @Column(name = "end_date")
    Date endDate;
    @Enumerated(EnumType.STRING)
    Country country;
    @Column(name = "instructor_name")
    String instructorName;

    // TODO: create the relationship to student
    @Transient
    Student student;

    //
    // Constructors
    //

    public Card() {
    }

    public Card(Long id, String course, String studentName, Date startDate, Date endDate, Country country,
                String instructorName) {
        this.id = id;
        this.course = course;
        this.studentName = studentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.instructorName = instructorName;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    //
    //
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id)
            && Objects.equals(course, card.course)
            && Objects.equals(studentName, card.studentName)
            && Objects.equals(startDate, card.startDate)
            && Objects.equals(endDate, card.endDate)
            && Objects.equals(country, card.country)
            && Objects.equals(instructorName, card.instructorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, studentName, startDate, endDate, country, instructorName);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", course='" + course + '\'' +
                ", studentName='" + studentName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", country=" + country +
                ", instructorName='" + instructorName  +
                '}';
    }
}
