package com.zem.diveschool.data;

import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface CourseDtoService extends CrudService<CourseDto, Long> {

    Set<SlotDto> findSlotsByCourseId(Long courseId);
    Optional<SlotDto> findByCourseIdAndSlotId(Long courseId, Long slotId);
}
