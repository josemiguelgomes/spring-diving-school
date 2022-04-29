package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class InstructorDtoToInstructor extends ConvertObject<InstructorDto, Instructor> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public Instructor convert(InstructorDto dto) {

 /*
        if (dto == null) {
            return null;
        }
  */


        final Instructor instructor = new Instructor();
        instructor.setId(dto.getId());
        instructor.setFirstName(dto.getFirstName());
        instructor.setMiddleName(dto.getMiddleName());
        instructor.setLastName(dto.getLastName());
        try {
            instructor.setBirthDate(dto.getSubmissionBirthDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            try {
                instructor.setBirthDate(sdf.parse("0001-01-01"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            // throw new RuntimeException(e);   // TODO: fix this exception and also the callers
        }
        instructor.setGender(dto.getGender());
        instructor.setEmail(dto.getEmail());
        instructor.setPhoneNumber(dto.getPhoneNumber());
        instructor.setLanguage(dto.getLanguage());
        instructor.setPhoto(dto.getPhoto());
        instructor.setStatusTeaching(dto.getStatusTeaching());

        return instructor;
    }

}
