package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.InstructorDto;
import com.zem.zemdivingschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.TimeZone;

@Component
public class InstructorDtoToInstructor implements Converter<InstructorDto, Instructor> {
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
            throw new RuntimeException(e);
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
