package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.CardDto;
import com.zem.zemdivingschool.persistence.model.Card;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.TimeZone;

@Component
public class CardDtoToCard implements Converter<CardDto, Card> {
    @Nullable
    @Override
    public Card convert(CardDto dto) {
 /*
        if (dto == null) {
            return null;
        }
  */
        final Card card = new Card();
        card.setId(dto.getId());
        card.setCourse(dto.getCourse());
        card.setStudentName(dto.getStudentName());
        try {
            card.setStartDate(dto.getSubmissionStartDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            card.setEndDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        card.setCountry(dto.getCountry());
        card.setInstructorName(dto.getInstructorName());

        return card;
    }
}
