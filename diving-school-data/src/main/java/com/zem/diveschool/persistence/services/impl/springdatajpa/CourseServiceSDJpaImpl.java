package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.repositories.CourseRepository;
import com.zem.diveschool.persistence.services.CourseService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile({"default", "springdatajpa"})
public class CourseServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Course, Long, CourseRepository>
        implements CourseService {

    public CourseServiceSDJpaImpl(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return super.getObjectRepository().findByName(name);
    }
}
