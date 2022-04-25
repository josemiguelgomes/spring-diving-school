package com.zem.diveschool.persistence.services;

import com.zem.diveschool.persistence.model.Instructor;

import java.util.Optional;

public interface InstructorService extends CrudService<Instructor, Long> {

    //
    Optional<Instructor> findByFirstName(String firstName);

}