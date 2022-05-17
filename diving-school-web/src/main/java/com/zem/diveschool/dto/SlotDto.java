package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class SlotDto extends GenericDto<SlotDto> {

    private String title;
    private String startDate;
    private String endDate;
    private SlotStatus status;

    private CourseDto course;
    private Set<SlotLanguageDto> languages = new HashSet<>();
    private Set<StudentDto> students = new HashSet<>();
    private Set<InstructorDto> instructors = new HashSet<>();

    @Builder
    public SlotDto(Long id, String title, String startDate, String endDate, SlotStatus status, CourseDto course,
                   Set<SlotLanguageDto> languages, Set<StudentDto> students, Set<InstructorDto> instructors) {
        super(id);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.course = course;
        this.languages = languages;
        this.students = students;
        this.instructors = instructors;
    }

    //
    // Conversions
    //
    public Date getSubmissionStartDateConverted(String timezone) {
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
        }    }

    public void setSubmissionStartDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.startDate = dateFormat.format(date);
    }

    public Date getSubmissionEndDateConverted(String timezone) {
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
