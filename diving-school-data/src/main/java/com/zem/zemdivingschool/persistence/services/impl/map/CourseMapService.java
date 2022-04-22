package com.zem.zemdivingschool.persistence.services.impl.map;

import com.zem.zemdivingschool.persistence.model.Course;
import com.zem.zemdivingschool.persistence.services.CourseService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Course object) {
        super.delete(object);
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
    public Course findById(Long id) {
        return super.findById(id);
    }
}
