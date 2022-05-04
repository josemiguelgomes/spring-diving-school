package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class InstructorDtoToInstructorImpl extends ConvertObject<InstructorDto, Instructor> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private final ConvertObjectToObject<SlotDto, Slot> slotConverterToEntity;

    public InstructorDtoToInstructorImpl(ConvertObjectToObject<SlotDto, Slot> slotConverterToEntity) {
        this.slotConverterToEntity = slotConverterToEntity;
    }

    @Synchronized
    @Nullable
    @Override
    public Instructor convert(InstructorDto dto) {
        if (dto == null) {
            return null;
        }
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
                .slots(slotConverterToEntity.convert(dto.getSlots()))
                .build();
    }

}
