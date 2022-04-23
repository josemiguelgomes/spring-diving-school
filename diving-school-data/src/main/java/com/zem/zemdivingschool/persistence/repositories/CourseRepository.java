package com.zem.zemdivingschool.persistence.repositories;

import com.zem.zemdivingschool.persistence.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {

    //
    Optional<Course> findByName(String name);
}