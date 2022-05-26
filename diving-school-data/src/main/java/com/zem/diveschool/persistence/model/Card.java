package com.zem.diveschool.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card extends BaseEntity<Card> {
    @Column(name = "course")
    private String course;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "start_date")
    private Date startDate = new Date();
    @Column(name = "end_date")
    private Date endDate = new Date();
//    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;
    @Column(name = "instructor_name")
    private String instructorName;

    @ManyToOne//(fetch = FetchType.LAZY, optional = true)
    private Student student;

    @Builder
    public Card(Long id,
                String course,
                String studentName,
                Date startDate,
                Date endDate,
                Country country,
                String instructorName,
                Student student) {
        super(id);
        this.course = course;
        this.studentName = studentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.instructorName = instructorName;
        this.student = student;
    }
}
