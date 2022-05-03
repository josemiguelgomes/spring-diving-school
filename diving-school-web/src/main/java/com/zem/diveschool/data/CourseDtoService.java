package com.zem.diveschool.data;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;

public interface CourseDtoService extends CrudService<CourseDto, Long> {

    Optional<CourseDto> findByName(String name);
}
