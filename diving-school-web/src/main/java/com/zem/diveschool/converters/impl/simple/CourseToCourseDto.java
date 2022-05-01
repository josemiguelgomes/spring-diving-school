package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDto extends ConvertObject<Course, CourseDto> {
    @Nullable
    @Override
    public CourseDto convert(Course entity) {
        return CourseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .level(entity.getLevel())
                .build();

    }
}
