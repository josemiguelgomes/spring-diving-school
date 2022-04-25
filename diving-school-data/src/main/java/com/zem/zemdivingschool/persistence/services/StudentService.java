package com.zem.zemdivingschool.persistence.services;

import com.zem.zemdivingschool.persistence.model.Student;

import java.util.Optional;

public interface StudentService extends CrudService<Student, Long> {

    //
    Optional<Student> findByFirstName(String firstName);

}