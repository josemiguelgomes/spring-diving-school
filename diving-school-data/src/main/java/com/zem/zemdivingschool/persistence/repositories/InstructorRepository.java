package com.zem.zemdivingschool.persistence.repositories;

import com.zem.zemdivingschool.persistence.model.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    //
}