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

    private LocationDto homeAddress;
    private Set<SlotDto> slots = new HashSet<>();

    @Builder
    public InstructorDto(Long id, String firstName, String middleName, String lastName, String birthDate,
                         Gender gender, String email, String phoneNumber, Language language, Byte[] photo,
                         StatusTeaching statusTeaching, LocationDto homeAddress, Set<SlotDto> slots) {
        super(id);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.photo = photo;
        this.statusTeaching = statusTeaching;
        this.homeAddress = homeAddress;
        this.slots = slots;
    }

    //
    // Conversions
    //
    public Date getSubmissionBirthDateConverted(String timezone)  {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            return dateFormat.parse(this.birthDate);
        } catch (ParseException e) {
//            throw new RuntimeException(e);
            try {
                return dateFormat.parse("0001-01-01");
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void setSubmissionBirthDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.birthDate = dateFormat.format(date);
    }

}
