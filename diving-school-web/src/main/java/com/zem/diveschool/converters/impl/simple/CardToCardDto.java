package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class CardToCardDto extends ConvertObject<Card, CardDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Nullable
    @Override
    public CardDto convert(Card entity) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().toString()));
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


}
