package com.zem.diveschool.persistence.services;

import com.zem.diveschool.persistence.model.Course;

import java.util.Optional;

public interface CourseService extends CrudService<Course, Long> {

    //
    Optional<Course> findByName(String name);
}