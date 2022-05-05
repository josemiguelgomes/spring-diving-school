package com.zem.diveschool.data;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface CourseDtoService extends CrudService<CourseDto, Long> {

    Optional<CourseDto> findByName(String name);

    Set<CourseDto> findBySlotId(Long slotId);
    Optional<CourseDto> findBySlotIdAndCourseId(Long slotId, Long courseId);
}
