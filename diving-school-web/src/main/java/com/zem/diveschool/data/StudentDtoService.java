package com.zem.diveschool.data;

import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface StudentDtoService extends CrudService<StudentDto, Long> {
    Set<CardDto> findCardsByStudentId(Long studentId);
    Optional<CardDto> findByStudentIdAndCardId(Long studentId, Long cardId);
    Set<LocationDto> findLocationsByStudentId(Long studentId);
    Optional<LocationDto> findByStudentIdAndLocationId(Long studentId, Long locationId);
    Set<SlotDto> findSlotsByStudentId(Long studentId);
    Optional<SlotDto> findByStudentIdAndSlotId(Long studentId, Long slotId);

    void deleteByStudentIdAndCardId(long studentId, long cardId);

    void deleteByStudentIdAndLocationId(long studentId, long locationId);

    void deleteByStudentIdAndSlotId(long studentId, long slotId);
}
