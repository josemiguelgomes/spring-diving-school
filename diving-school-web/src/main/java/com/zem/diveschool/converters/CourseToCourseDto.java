package com.zem.diveschool.converters;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDto implements Converter<Course, CourseDto> {
    @Nullable
    @Override
    public CourseDto convert(Course entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final CourseDto courseDto = new CourseDto();

        courseDto.setId(entity.getId());
        courseDto.setName(entity.getName());
        courseDto.setLevel(entity.getLevel());

        return courseDto;
    }
}
