package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.services.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
public class CourseDtoServiceImpl extends AbstractDtoServiceImpl<CourseDto, Long, Course, CourseService>
                                implements CourseDtoService {

    @Override
    public Set<CourseDto> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<CourseDto> findById(Long id) {
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
    public Set<SlotDto> findSlotsByCourseId(Long courseId) {
        return service.findById(courseId)
                .stream()
                .map(p -> entityToDto.convert(p))
                .map(CourseDto::getSlots)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<SlotDto> findByCourseIdAndSlotId(Long courseId, Long slotId) {
        return findSlotsByCourseId(courseId)
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public void deleteByCourseIdAndSlotId(Long courseId, Long slotId) {
        // Step 1 - Get Course and Slot
        CourseDto courseDto =  entityToDto.convert(service.findById(courseId).get());
        Optional<SlotDto> slotDtoOptionalToBeRemoved = courseDto
                .getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();

        // Step 2 - remove link course -> slot
        courseDto.getSlots().remove(slotDtoOptionalToBeRemoved.get());

        // Step 3 - remove link slot -> course
        slotDtoOptionalToBeRemoved.get().setCourse(null);

        // Step 4 - persist on database
        service.save(dtoToEntity.convert(courseDto));
    }
}

