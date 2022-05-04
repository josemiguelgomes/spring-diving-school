package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class StudentToStudentDtoImpl extends ConvertObject<Student, StudentDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final ConvertObjectToObject<Card, CardDto> cardConverterToDto;
    private final ConvertObjectToObject<Slot, SlotDto> slotConverterToDto;


    public StudentToStudentDtoImpl(ConvertObjectToObject<Card, CardDto> cardConverterToDto,
                                   ConvertObjectToObject<Slot, SlotDto> slotConverterToDto) {
        this.cardConverterToDto = cardConverterToDto;
        this.slotConverterToDto = slotConverterToDto;
    }

    @Synchronized
    @Nullable
    @Override
    public StudentDto convert(Student entity) {
        if (entity == null) {
            return null;
        }
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
                .cards(cardConverterToDto.convert(entity.getCards()))
                .slots(slotConverterToDto.convert(entity.getSlots()))
                .build();
    }
}
