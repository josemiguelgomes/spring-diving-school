package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.StudentDto;
import com.zem.zemdivingschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class StudentToStudentDto implements Converter<Student, StudentDto> {
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
