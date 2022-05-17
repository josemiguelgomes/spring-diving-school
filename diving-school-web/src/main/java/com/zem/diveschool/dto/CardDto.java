package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Country;
import com.zem.diveschool.persistence.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
public class CardDto extends GenericDto<CardDto> {

    private String course;
    private String studentName;
    private String startDate ;
    private String endDate;
    private Country country;
    private String instructorName;

    private StudentDto student;

    @Builder
    public CardDto(Long id, String course, String studentName, String startDate, String endDate, Country country,
                   String instructorName, StudentDto student) {
        super(id);
        this.course = course;
        this.studentName = studentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.instructorName = instructorName;
        this.student = student;
    }

    //
    // Conversions
    //
    public Date getSubmissionStartDateConverted(String timezone)  {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            return dateFormat.parse(this.startDate);
        } catch (ParseException | NullPointerException e) {
//            throw new RuntimeException(e);
            try {
                return dateFormat.parse("0001-01-01");
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void setSubmissionStartDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.startDate = dateFormat.format(date);
    }

    public Date getSubmissionEndDateConverted(String timezone)  {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            return dateFormat.parse(this.endDate);
        } catch (ParseException | NullPointerException e) {
//            throw new RuntimeException(e);
            try {
                return dateFormat.parse("0001-01-01");
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void setSubmissionEndDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.endDate = dateFormat.format(date);
    }
}
