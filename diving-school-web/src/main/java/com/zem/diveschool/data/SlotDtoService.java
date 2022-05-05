package com.zem.diveschool.data;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface SlotDtoService extends CrudService<SlotDto, Long> {

    Set<SlotDto> findByCourseId(Long courseId);

    Optional<SlotDto> findByCourseIdAndSlotId(Long courseId, Long slotId);

    Set<SlotDto> findByInstructorId(Long instructorId);

    Optional<SlotDto> findByInstructorIdAndSlotId(Long instructorID, Long slotId);

    Set<SlotDto> findByStudentId(Long studentId);

    Optional<SlotDto> findByStudentIdAndSlotId(Long studentId, Long slotId);
}
