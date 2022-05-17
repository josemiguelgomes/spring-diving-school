package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.Instructor;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class InstructorConverter extends SchoolSimpleConverter<InstructorDto, Instructor> {

    public InstructorConverter() {
        super(InstructorConverter::convertToEntity, InstructorConverter::convertToDto);
    }

    @Synchronized
    private static InstructorDto convertToDto(Instructor entity) {
        return InstructorDto.builder()
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
                .statusTeaching(entity.getStatusTeaching())
                .build();
    }

    @Synchronized
    private static Instructor convertToEntity(InstructorDto dto) {
        return Instructor.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .birthDate(dto.getSubmissionBirthDateConverted(TimeZone.getDefault().toString()))
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .language(dto.getLanguage())
                .photo(dto.getPhoto())
                .statusTeaching(dto.getStatusTeaching())
                .build();
    }
}
