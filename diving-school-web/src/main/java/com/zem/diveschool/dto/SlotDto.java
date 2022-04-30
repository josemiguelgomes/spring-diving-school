package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class SlotDto extends GenericDto<SlotDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private SlotStatus status;

    private Course course;
    private Set<SlotLanguage> languages = new HashSet<>();
    private Set<Student> students = new HashSet<>();
    private Set<Instructor> instructors = new HashSet<>();

    //
    // Conversions
    //
    public Date getSubmissionStartDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.startDate);
    }

    public void setSubmissionStartDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.startDate = dateFormat.format(date);
    }

    public Date getSubmissionEndDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.endDate);
    }

    public void setSubmissionEndDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.endDate = dateFormat.format(date);
    }
}
