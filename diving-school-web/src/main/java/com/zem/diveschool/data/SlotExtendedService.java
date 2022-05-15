package com.zem.diveschool.data;

import com.zem.diveschool.persistence.model.*;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface SlotExtendedService extends CrudService<Slot, Long> {
    Set<Course> findCoursesBySlotId(Long slotId);
    Optional<Course> findBySlotIdAndCourseId(Long slotId, Long courseId);

    Set<Instructor> findInstructorsBySlotId(Long slotId);

    Optional<Instructor> findBySlotIdAndInstructorId(Long slotId, Long instructorId);

    Set<SlotLanguage> findLanguagesBySlotId(Long slotId);
    Optional<SlotLanguage> findBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId);

    Set<Student> findStudentsBySlotId(Long slotId);
    Optional<Student> findBySlotIdAndStudentId(Long slotId, Long studentId);

    void deleteBySlotIdAndCourseId(Long slotId, Long courseId);

    void deleteBySlotIdAndInstructorId(Long slotId, Long instructorId);

    void deleteBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId);

    void deleteBySlotIdAndStudentId(Long slotId, Long studentId);
}
