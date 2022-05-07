package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.SlotService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class SlotDtoServiceImpl extends AbstractDtoServiceImpl<SlotDto, Long, Slot, SlotService>
                                implements SlotDtoService {


    @Override
    public Set<SlotDto> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<SlotDto> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public SlotDto save(SlotDto object) {
        return super.save(object);
    }

    @Override
    public void delete(SlotDto object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends SlotDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
        return super.saveAll(dtos);
    }

    @Override
    public Set<CourseDto> findCoursesBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(p -> entityToDto.convert(p))
                //.map(SlotDto::getCourses) // TODO #93
                .map(SlotDto::getCourse)// TODO #93
                .collect(toSet());
    }

    @Override
    public Optional<CourseDto> findBySlotIdAndCourseId(Long slotId, Long courseId) {
        return findCoursesBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(courseId))
                .findFirst();
    }

    @Override
    public Set<InstructorDto> findInstructorsBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(p -> entityToDto.convert(p))
                .map(SlotDto::getInstructors)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<InstructorDto> findBySlotIdAndInstructorId(Long slotId, Long instructorId) {
        return findInstructorsBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(instructorId))
                .findFirst();
    }

    @Override
    public Set<SlotLanguageDto> findLanguagesBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(p -> entityToDto.convert(p))
                .map(SlotDto::getLanguages)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<SlotLanguageDto> findBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId) {
        return findLanguagesBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(slotLanguageId))
                .findFirst();
    }

    @Override
    public Set<StudentDto> findStudentsBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(p -> entityToDto.convert(p))
                .map(p -> p.getStudents())
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<StudentDto> findBySlotIdAndStudentId(Long slotId, Long studentId) {
        return findStudentsBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(studentId))
                .findFirst();
    }
}

