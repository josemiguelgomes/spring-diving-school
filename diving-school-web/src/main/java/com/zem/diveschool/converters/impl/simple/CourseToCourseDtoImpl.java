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
public class CourseToCourseDtoImpl extends ConvertObject<Course, CourseDto> {

    private final ConvertObjectToObject<Slot, SlotDto> slotConverterToDto;

    public CourseToCourseDtoImpl(ConvertObjectToObject<Slot, SlotDto> slotConverterToDto) {
        this.slotConverterToDto = slotConverterToDto;
    }

    @Synchronized
    @Nullable
    @Override
    public CourseDto convert(Course entity) {
        if (entity == null) {
            return null;
        }
        return CourseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .level(entity.getLevel())
                .slots(slotConverterToDto.convert(entity.getSlots()))
                .build();

    }
}
