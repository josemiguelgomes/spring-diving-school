package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.SlotService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SlotDtoServiceImpl extends AbstractDtoServiceImpl<SlotDto, Long, Slot, SlotService>
                                implements SlotDtoService {


    @Override
    public Set<SlotDto> findAll() {
        return super.findAll();
    }

    @Override
    public SlotDto findById(Long id) {
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
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        // TODO #93
        Set<CourseDto> coursesDto = new HashSet<>();
        coursesDto.add(slotDto.getCourse());
        return coursesDto;
    }

    @Override
    public Optional<CourseDto> findBySlotIdAndCourseId(Long slotId, Long courseId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        // TODO #93
        Optional<CourseDto> courseDto = Optional.of(slotDto.getCourse());
        return courseDto
                .stream()
                .filter(p -> p.getId().equals(courseId))
                .findFirst();
    }

    @Override
    public Set<InstructorDto> findInstructorsBySlotId(Long slotId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        return slotDto.getInstructors();
    }

    @Override
    public Optional<InstructorDto> findBySlotIdAndInstructorId(Long slotId, Long instructorId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        return slotDto.getInstructors()
                .stream()
                .filter(p -> p.getId().equals(instructorId))
                .findFirst();
    }

    @Override
    public Set<SlotLanguageDto> findLanguagesBySlotId(Long slotId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        return slotDto.getLanguages();
    }

    @Override
    public Optional<SlotLanguageDto> findBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        return slotDto.getLanguages()
                .stream()
                .filter(p -> p.getId().equals(slotLanguageId))
                .findFirst();
    }

    @Override
    public Set<StudentDto> findStudentsBySlotId(Long slotId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        return slotDto.getStudents();
    }

    @Override
    public Optional<StudentDto> findBySlotIdAndStudentId(Long slotId, Long studentId) {
        SlotDto slotDto = entityToDto.convert(service.findById(slotId));
        return slotDto.getStudents()
                .stream()
                .filter(p -> p.getId().equals(studentId))
                .findFirst();
    }
}

