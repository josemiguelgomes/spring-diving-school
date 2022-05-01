package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoToCourse extends ConvertObject<CourseDto, Course> {
    @Nullable
    @Override
    public Course convert(CourseDto dto) {
        return Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .level(dto.getLevel())
                .build();
    }

}
