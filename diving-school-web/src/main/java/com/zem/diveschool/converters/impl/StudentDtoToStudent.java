package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class StudentDtoToStudent extends ConvertObject<StudentDto, Student> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public Student convert(StudentDto dto) {
 /*
        if (dto == null) {
            return null;
        }
  */
        final Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setMiddleName(dto.getMiddleName());
        student.setLastName(dto.getLastName());
        try {
            student.setBirthDate(dto.getSubmissionBirthDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            try {
                student.setBirthDate(sdf.parse("0001-01-01"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            // throw new RuntimeException(e);   // TODO: fix this exception and also the callers
        }
        student.setGender(dto.getGender());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setLanguage(dto.getLanguage());
        student.setPhoto(dto.getPhoto());

        return student;
    }
}
