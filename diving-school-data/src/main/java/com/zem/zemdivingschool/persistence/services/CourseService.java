package com.zem.zemdivingschool.persistence.services;

import com.zem.zemdivingschool.persistence.model.Course;

import java.util.Optional;

public interface CourseService extends CrudService<Course, Long> {
    Optional<Course> findByName(String name);
}
