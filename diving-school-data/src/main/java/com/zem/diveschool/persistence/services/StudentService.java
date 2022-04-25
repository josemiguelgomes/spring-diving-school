package com.zem.diveschool.persistence.services;

import com.zem.diveschool.persistence.model.Student;

import java.util.Optional;

public interface StudentService extends CrudService<Student, Long> {

    //
    Optional<Student> findByFirstName(String firstName);

}