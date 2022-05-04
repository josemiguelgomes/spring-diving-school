package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Slot;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoToCourseImpl extends ConvertObject<CourseDto, Course> {

    private final ConvertObjectToObject<SlotDto, Slot> slotConverterToEntity;

    public CourseDtoToCourseImpl(ConvertObjectToObject<SlotDto, Slot> slotConverterToEntity) {
        this.slotConverterToEntity = slotConverterToEntity;
    }

    @Synchronized
    @Nullable
    @Override
    public Course convert(CourseDto dto) {
        if (dto == null) {
            return null;
        }
        return Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .level(dto.getLevel())
                .slots(slotConverterToEntity.convert(dto.getSlots()))
                .build();
    }

}
