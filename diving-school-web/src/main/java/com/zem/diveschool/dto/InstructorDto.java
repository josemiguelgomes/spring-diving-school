package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class InstructorDto extends GenericDto<InstructorDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private Language language;
    private Byte[] photo;
    private StatusTeaching statusTeaching;

    private Location homeAddress;
    private Set<Slot> slots = new HashSet<>();


    //
    // Conversions
    //
    public Date getSubmissionBirthDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.birthDate);
    }

    public void setSubmissionBirthDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.birthDate = dateFormat.format(date);
    }

}
