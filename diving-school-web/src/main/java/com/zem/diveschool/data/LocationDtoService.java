package com.zem.diveschool.data;

import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface LocationDtoService extends CrudService<LocationDto, Long> {

    Set<LocationDto> findByInstructorId(Long instructorId);

    Optional<LocationDto> findByInstructionAndLocationId(Long instructorId, Long locationId);

    Set<LocationDto> findBySlotId(Long slotId);

    Optional<LocationDto> findBySlotIdAndLocationID(Long slotId, Long locationId);

    Set<LocationDto> findByStudentId(Long studentId);

    Optional<LocationDto> findByStudentIdAndLocationId(Long studentId, Long locationId);
}
