package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentDtoServiceImpl extends AbstractDtoServiceImpl<StudentDto, Long, Student, StudentService>
                                implements StudentDtoService {

    @Override
    public Set<StudentDto> findAll() {
        return super.findAll();
    }

    @Override
    public StudentDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public StudentDto save(StudentDto object) {
       return super.save(object);
    }

    @Override
    public void delete(StudentDto object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends StudentDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
       return super.saveAll(dtos);
    }

    @Override
    public Set<CardDto> findCardsByStudentId(Long studentId) {
        StudentDto studentDto = entityToDto.convert(service.findById(studentId));
        return studentDto.getCards();
    }

    @Override
    public Optional<CardDto> findByStudentIdAndCardId(Long studentId, Long cardId) {
        StudentDto studentDto = entityToDto.convert(service.findById(studentId));
        return studentDto.getCards()
                .stream()
                .filter(p -> p.getId().equals(cardId))
                .findFirst();
    }

    @Override
    public Set<LocationDto> findLocationsByStudentId(Long studentId) {
        StudentDto studentDto = entityToDto.convert(service.findById(studentId));
        // TODO #93
        Set<LocationDto> locationsDto = new HashSet<>();
        locationsDto.add(studentDto.getHomeAddress());
        return locationsDto;
    }

    @Override
    public Optional<LocationDto> findByStudentIdAndLocationId(Long studentId, Long locationId) {
        StudentDto studentDto = entityToDto.convert(service.findById(studentId));
        // TODO #93
        return Optional.of(studentDto.getHomeAddress())
                .stream()
                .filter(p -> p.getId().equals(locationId))
                .findFirst();
    }

    @Override
    public Set<SlotDto> findSlotsByStudentId(Long studentId) {
        StudentDto studentDto = entityToDto.convert(service.findById(studentId));
        return studentDto.getSlots();
    }

    @Override
    public Optional<SlotDto> findByStudentIdAndSlotId(Long studentId, Long slotId) {
        StudentDto studentDto = entityToDto.convert(service.findById(studentId));
        return studentDto.getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }
}

