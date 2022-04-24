package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.StudentDto;
import com.zem.zemdivingschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.TimeZone;

@Component
public class StudentDtoToStudent implements Converter<StudentDto, Student> {
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
            throw new RuntimeException(e);
        }
        student.setGender(dto.getGender());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setLanguage(dto.getLanguage());
        student.setPhoto(dto.getPhoto());

        return student;
    }
}
