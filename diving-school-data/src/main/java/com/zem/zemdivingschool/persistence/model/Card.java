package com.zem.zemdivingschool.persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {
    @Column(name = "course")
    private String course;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "start_date")
    private Date startDate ;
    @Column(name = "end_date")
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "instructor_name")
    private String instructorName;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private Student student;

    //
    // Constructors
    //

    public Card() {
        super();
    }

    public Card(Long id, String course, String studentName, Date startDate, Date endDate, Country country,
                String instructorName) {
        super(id);
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
        return Objects.equals(getId(), card.getId())
            && Objects.equals(course, card.course)
            && Objects.equals(studentName, card.studentName)
            && Objects.equals(startDate, card.startDate)
            && Objects.equals(endDate, card.endDate)
            && Objects.equals(country, card.country)
            && Objects.equals(instructorName, card.instructorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), course, studentName, startDate, endDate, country, instructorName);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + getId() +
                ", course='" + course + '\'' +
                ", studentName='" + studentName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", country=" + country +
                ", instructorName='" + instructorName  +
                '}';
    }
}
