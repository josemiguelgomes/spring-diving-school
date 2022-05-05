package com.zem.diveschool.data;

import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface InstructorDtoService extends CrudService<InstructorDto, Long> {

    Optional<InstructorDto> findByFirstName(String firstName);

    Set<InstructorDto> findBySlotId(Long slotId);

    Optional<InstructorDto> findBySlotIdAndInstructorId(Long slotId, Long instructorId);
}
