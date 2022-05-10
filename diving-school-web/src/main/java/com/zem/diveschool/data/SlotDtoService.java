package com.zem.diveschool.data;

import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface SlotDtoService extends CrudService<SlotDto, Long> {
    Set<CourseDto> findCoursesBySlotId(Long slotId);
    Optional<CourseDto> findBySlotIdAndCourseId(Long slotId, Long courseId);

    Set<InstructorDto> findInstructorsBySlotId(Long slotId);

    Optional<InstructorDto> findBySlotIdAndInstructorId(Long slotId, Long instructorId);

    Set<SlotLanguageDto> findLanguagesBySlotId(Long slotId);
    Optional<SlotLanguageDto> findBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId);

    Set<StudentDto> findStudentsBySlotId(Long slotId);
    Optional<StudentDto> findBySlotIdAndStudentId(Long slotId, Long studentId);

    void deleteBySlotIdAndCourseId(Long slotId, Long courseId);

    void deleteBySlotIdAndInstructorId(Long slotId, Long instructorId);

    void deleteBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId);

    void deleteBySlotIdAndStudentId(Long slotId, Long studentId);
}
