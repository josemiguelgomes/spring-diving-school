package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class CardConverter extends SchoolSimpleConverter<CardDto, Card> {

    public CardConverter() {
        super(CardConverter::convertToEntity, CardConverter::convertToDto);
    }

    @Synchronized
    private static CardDto convertToDto(Card entity) {
        return CardDto.builder()
                .id(entity.getId())
                .course(entity.getCourse())
                .studentName(entity.getStudentName())
                .startDate(dateFormat.format(entity.getStartDate()))
                .endDate(dateFormat.format(entity.getEndDate()))
                .country(entity.getCountry())
                .instructorName(entity.getInstructorName())
                .build();
    }

    @Synchronized
    private static Card convertToEntity(CardDto dto) {
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
