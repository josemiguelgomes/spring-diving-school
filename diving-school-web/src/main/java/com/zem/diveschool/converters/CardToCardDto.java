package com.zem.diveschool.converters;

import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class CardToCardDto implements Converter<Card, CardDto> {
    @Nullable
    @Override
    public CardDto convert(Card entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final CardDto cardDto = new CardDto();

        cardDto.setId(entity.getId());
        cardDto.setCourse(entity.getCourse());
        cardDto.setStudentName(entity.getStudentName());
        cardDto.setSubmissionStartDate(entity.getStartDate(), TimeZone.getDefault().toString());
        cardDto.setSubmissionEndDate(entity.getEndDate(), TimeZone.getDefault().toString());
        cardDto.setCountry(entity.getCountry());
        cardDto.setInstructorName(entity.getInstructorName());

        return cardDto;
    }
}
