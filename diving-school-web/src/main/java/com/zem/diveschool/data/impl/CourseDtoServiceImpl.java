package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.services.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseDtoServiceImpl extends AbstractDtoServiceImpl<CourseDto, Long, Course, CourseService>
                                implements CourseDtoService {

    @Override
    public Set<CourseDto> findAll() {
        return super.findAll();
    }

    @Override
    public CourseDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public CourseDto save(CourseDto object) {
       return super.save(object);
    }

    @Override
    public void delete(CourseDto object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends CourseDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
       return super.saveAll(dtos);
    }

    @Override
    public Optional<CourseDto> findByName(String name) {
        return entityToDto.convert(service.findByName(name));
    }

    @Override
    @Transactional
    public Set<CourseDto> findBySlotId(Long slotId) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<CourseDto> findBySlotIdAndCourseId(Long slotId, Long courseId) {
        // TODO
        return Optional.empty();
    }
}

