package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoToCourseImpl extends ConvertObject<CourseDto, Course> {
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
                .build();
    }

}
