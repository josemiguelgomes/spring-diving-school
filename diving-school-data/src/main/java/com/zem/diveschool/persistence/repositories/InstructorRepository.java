package com.zem.diveschool.persistence.repositories;

import com.zem.diveschool.persistence.model.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    Optional<Instructor> findByFirstName(String firstName);
}
