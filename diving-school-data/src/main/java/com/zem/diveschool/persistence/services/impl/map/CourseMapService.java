package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.services.CourseService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class CourseMapService extends AbstractMapService<Course, Long>
                              implements CourseService {
    @Override
    @Transactional
    public Set<Course> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional
    public Course findById(Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course object) {
        if (object == null) {
            return null;
        }

        return super.save(object);
    }

    @Override
    @Transactional
    public void delete(Course object) {
        super.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Course> findByName(String name) {
        for(Course course : map.values()) {
            if (course.getName().equals(name)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }
}
