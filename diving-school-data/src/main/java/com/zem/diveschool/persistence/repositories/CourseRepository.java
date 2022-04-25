package com.zem.diveschool.persistence.repositories;

import com.zem.diveschool.persistence.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findByName(String name);
}
