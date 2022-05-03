package com.zem.diveschool.data;

import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;

public interface InstructorDtoService extends CrudService<InstructorDto, Long> {

    Optional<InstructorDto> findByFirstName(String firstName);
}
