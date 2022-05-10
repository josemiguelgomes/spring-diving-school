package com.zem.diveschool.data;

import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface InstructorDtoService extends CrudService<InstructorDto, Long> {
    Set<SlotDto> findSlotsByInstructorId(Long instructorId);

    Optional<SlotDto> findByInstructorIdAndSlotId(Long instructorId, Long slotId);

    Set<LocationDto> findLocationsByInstructorId(Long instructorId);
    Optional<LocationDto> findByInstructorIdAndLocationId(Long instructorId, Long locationId);

    void deleteByInstructorIdAndLocationId(Long instructorId, Long locationId);

    void deleteByInstructorIdAndSlotId(Long instructorId, Long slotId);
}
