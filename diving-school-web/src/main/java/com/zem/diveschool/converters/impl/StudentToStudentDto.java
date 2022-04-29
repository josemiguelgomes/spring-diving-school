package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class StudentToStudentDto extends ConvertObject<Student, StudentDto> {
    @Nullable
    @Override
    public StudentDto convert(Student entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final StudentDto studentDto = new StudentDto();

        studentDto.setId(entity.getId());
        studentDto.setFirstName(entity.getFirstName());
        studentDto.setMiddleName(entity.getMiddleName());
        studentDto.setLastName(entity.getLastName());
        studentDto.setSubmissionBirthDate(entity.getBirthDate(), TimeZone.getDefault().toString());
        studentDto.setGender(entity.getGender());
        studentDto.setEmail(entity.getEmail());
        studentDto.setPhoneNumber(entity.getPhoneNumber());
        studentDto.setLanguage(entity.getLanguage());
        studentDto.setPhoto(entity.getPhoto());


        return studentDto;
    }
}
