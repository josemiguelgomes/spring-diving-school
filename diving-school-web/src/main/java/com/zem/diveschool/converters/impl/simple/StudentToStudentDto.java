package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class StudentToStudentDto extends ConvertObject<Student, StudentDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Synchronized
    @Nullable
    @Override
    public StudentDto convert(Student entity) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().toString()));
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
}
