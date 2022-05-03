package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class InstructorToInstructorDtoImpl extends ConvertObject<Instructor, InstructorDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Synchronized
    @Nullable
    @Override
    public InstructorDto convert(Instructor entity) {
        if (entity == null) {
            return null;
        }
        dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().toString()));
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
}
