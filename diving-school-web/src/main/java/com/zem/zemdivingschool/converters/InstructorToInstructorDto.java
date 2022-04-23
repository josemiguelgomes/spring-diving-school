package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.InstructorDto;
import com.zem.zemdivingschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class InstructorToInstructorDto implements Converter<Instructor, InstructorDto> {
    @Nullable
    @Override
    public InstructorDto convert(Instructor entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final InstructorDto instructorDto = new InstructorDto();

        instructorDto.setId(entity.getId());
        instructorDto.setFirstName(entity.getFirstName());
        instructorDto.setMiddleName(entity.getMiddleName());
        instructorDto.setLastName(entity.getLastName());
        instructorDto.setSubmissionBirthDate(entity.getBirthDate(), TimeZone.getDefault().toString());
        instructorDto.setGender(entity.getGender());
        instructorDto.setEmail(entity.getEmail());
        instructorDto.setPhoneNumber(entity.getPhoneNumber());
        instructorDto.setLanguage(entity.getLanguage());
        instructorDto.setPhoto(entity.getPhoto());
        instructorDto.setStatusTeaching(entity.getStatusTeaching());

        return instructorDto;
    }
}
