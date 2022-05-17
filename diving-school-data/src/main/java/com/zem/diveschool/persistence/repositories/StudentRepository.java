package com.zem.diveschool.persistence.repositories;

import com.zem.diveschool.persistence.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByFirstName(String firstName);

    Set<Student> findAllByLastNameLike(String lastName);
}
