package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter extends SchoolSimpleConverter<CourseDto, Course> {

    public CourseConverter() {
        super(CourseConverter::convertToEntity, CourseConverter::convertToDto);
    }

    @Synchronized
    private static CourseDto convertToDto(Course entity) {

        return CourseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .level(entity.getLevel())
                .build();
    }

    @Synchronized
    private static Course convertToEntity(CourseDto dto) {
        return Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .level(dto.getLevel())
                .build();
    }

}
