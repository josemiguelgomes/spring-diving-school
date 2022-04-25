package com.zem.diveschool.converters;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoToCourse implements Converter<CourseDto, Course> {
    @Nullable
    @Override
    public Course convert(CourseDto dto) {
 /*
        if (dto == null) {
            return null;
        }
  */
        final Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setLevel(dto.getLevel());

        return course;
    }
}
