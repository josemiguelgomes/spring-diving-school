package com.zem.zemdivingschool.persistence.services;

import com.zem.zemdivingschool.persistence.model.Instructor;

import java.util.Optional;

public interface InstructorService extends CrudService<Instructor, Long> {
    Optional<Instructor> findByFirstName(String firstName);
}
