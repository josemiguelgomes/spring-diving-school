package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class StudentDtoToStudentImpl extends ConvertObject<StudentDto, Student> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Synchronized
    @Nullable
    @Override
    public Student convert(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        return Student.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .birthdate(dto.getSubmissionBirthDateConverted(TimeZone.getDefault().toString()))
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .language(dto.getLanguage())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }
}
