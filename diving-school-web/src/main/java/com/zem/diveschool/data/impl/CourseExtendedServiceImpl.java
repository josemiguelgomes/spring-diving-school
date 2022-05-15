package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CourseExtendedService;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class CourseExtendedServiceImpl extends AbstractExtendedServiceImpl<Course, Long, CourseService>
                                implements CourseExtendedService {

    @Override
    public Set<Course> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Course save(Course object) {
       return super.save(object);
    }

    @Override
    public void delete(Course object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends Course> List<S> saveAll(@NotNull Iterable<S> entities) {
       return super.saveAll(entities);
    }

    @Override
    public Set<Slot> findSlotsByCourseId(Long courseId) {
        return service.findById(courseId)
                .stream()
                .map(Course::getSlots)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<Slot> findByCourseIdAndSlotId(Long courseId, Long slotId) {
        return findSlotsByCourseId(courseId)
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public void deleteByCourseIdAndSlotId(Long courseId, Long slotId) {
        // Step 1 - Get Course and Slot
        Optional<Course> courseOptional = service.findById(courseId);
        Optional<Slot> slotOptionalToBeRemoved = courseOptional.get()
                .getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();

        // Step 2 - remove link course -> slot
        courseOptional.get().getSlots().remove(slotOptionalToBeRemoved.get());

        // Step 3 - remove link slot -> course
        slotOptionalToBeRemoved.get().setCourse(null);

        // Step 4 - persist on database
        service.save(courseOptional.get());
    }
}

