package com.zem.diveschool.data;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface StudentDtoService extends CrudService<StudentDto, Long> {

    Optional<StudentDto> findByFirstName(String firstName);

    Set<StudentDto> findBySlotId(Long slotID);

    Optional<StudentDto> findBySlotIdAndStudentId(Long slotId, Long studentID);
}
