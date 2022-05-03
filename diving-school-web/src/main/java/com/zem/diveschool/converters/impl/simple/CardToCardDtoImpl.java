package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Student;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class CardToCardDtoImpl extends ConvertObject<Card, CardDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final ConvertObjectToObject<Student, StudentDto> convertStudent;

    public CardToCardDtoImpl(ConvertObjectToObject<Student, StudentDto> convertStudent) {
        this.convertStudent = convertStudent;
    }

    @Synchronized
    @Nullable
    @Override
    public CardDto convert(Card entity) {
        if (entity == null) {
            return null;
        }
        dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().toString()));

        return CardDto.builder()
                .id(entity.getId())
                .course(entity.getCourse())
                .studentName(entity.getStudentName())
                .startDate(dateFormat.format(entity.getStartDate()))
                .endDate(dateFormat.format(entity.getEndDate()))
                .country(entity.getCountry())
                .instructorName(entity.getInstructorName())
                .student(convertStudent.convert(entity.getStudent()))
                .build();
    }


}
