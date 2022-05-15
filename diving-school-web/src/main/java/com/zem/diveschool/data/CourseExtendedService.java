package com.zem.diveschool.data;

import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface CourseExtendedService extends CrudService<Course, Long> {

    Set<Slot> findSlotsByCourseId(Long courseId);
    Optional<Slot> findByCourseIdAndSlotId(Long courseId, Long slotId);

    void deleteByCourseIdAndSlotId(Long courseId, Long slotId);
}
