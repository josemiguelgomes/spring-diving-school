package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class CardDtoToCard extends ConvertObject<CardDto, Card> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            try {
                 card.setStartDate(sdf.parse("0001-01-01"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            // throw new RuntimeException(e);   // TODO: fix this exception and also the callers
        }
        try {
            card.setEndDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            try {
                card.setEndDate(sdf.parse("0001-01-01"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            // throw new RuntimeException(e);   // TODO: fix this exception and also the callers
        }
        card.setCountry(dto.getCountry());
        card.setInstructorName(dto.getInstructorName());

        return card;
    }

}
