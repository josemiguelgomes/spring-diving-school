package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class CardDtoToCard extends ConvertObject<CardDto, Card> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Synchronized
    @Nullable
    @Override
    public Card convert(CardDto dto) {
        return Card.builder()
                .id(dto.getId())
                .course(dto.getCourse())
                .studentName(dto.getStudentName())
                .startDate(dto.getSubmissionStartDateConverted(TimeZone.getDefault().toString()))
                .endDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()))
                .country(dto.getCountry())
                .instructorName(dto.getInstructorName())
                .build();
    }
}