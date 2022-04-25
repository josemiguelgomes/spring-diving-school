package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.Course;
import com.zem.zemdivingschool.persistence.repositories.CourseRepository;
import com.zem.zemdivingschool.persistence.services.CourseService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile({"default", "springdatajpa"})
public class CourseSDJpaService extends AbstractSDJpaService<Course, Long, CourseRepository>
        implements CourseService {

    protected CourseSDJpaService(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return super.getObjectRepository().findByName(name);
    }
}
