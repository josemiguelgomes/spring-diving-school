package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Student;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class StudentConverter extends SchoolSimpleConverter<StudentDto, Student> {

    public StudentConverter() {
        super(StudentConverter::convertToEntity, StudentConverter::convertToDto);
    }

    @Synchronized
    private static StudentDto convertToDto(Student entity) {
        return StudentDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .birthDate(dateFormat.format(entity.getBirthDate()))
                .gender(entity.getGender())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .language(entity.getLanguage())
                .photo(entity.getPhoto())
                .build();
    }

    @Synchronized
    private static Student convertToEntity(StudentDto dto) {
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
                .photo(dto.getPhoto())
                .build();
    }
}
