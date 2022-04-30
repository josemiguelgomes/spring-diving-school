package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Country;
import com.zem.diveschool.persistence.model.Student;
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
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String course;
    private String studentName;
    private String startDate ;
    private String endDate;
    private Country country;
    private String instructorName;

    private Student student;


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
