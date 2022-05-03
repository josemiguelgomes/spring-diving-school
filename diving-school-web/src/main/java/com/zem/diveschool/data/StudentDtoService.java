package com.zem.diveschool.data;

import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;

public interface StudentDtoService extends CrudService<StudentDto, Long> {

    Optional<StudentDto> findByFirstName(String firstName);
}
