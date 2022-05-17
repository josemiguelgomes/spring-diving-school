package com.zem.diveschool.persistence.services;

import com.zem.diveschool.persistence.model.Student;

import java.util.Optional;
import java.util.Set;

public interface StudentService extends CrudService<Student, Long> {

    //
    Optional<Student> findByFirstName(String firstName);

    void saveImageFile(Long studentId, Byte[] image);

    public Set<Student> findAllByLastNameLike(String lastName);
}