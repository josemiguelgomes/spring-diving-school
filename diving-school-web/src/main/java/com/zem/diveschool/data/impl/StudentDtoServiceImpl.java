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

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class StudentDtoServiceImpl extends AbstractDtoServiceImpl<StudentDto, Long, Student, StudentService>
                                implements StudentDtoService {

    @Override
    public Set<StudentDto> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<StudentDto> findById(Long id) {
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
        return service.findById(studentId)
                .stream()
                .map(p -> entityToDto.convert(p))
                .map(StudentDto::getCards)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<CardDto> findByStudentIdAndCardId(Long studentId, Long cardId) {
        return findCardsByStudentId(studentId)
                .stream()
                .filter(p -> p.getId().equals(cardId))
                .findFirst();
    }

    @Override
    public Set<LocationDto> findLocationsByStudentId(Long studentId) {
        return service.findById(studentId)
                .stream()
                .map(p -> entityToDto.convert(p))
//              .map(p -> p.getLocations())// TODO #93
//              .flatMap(Collection::stream)
                .map(StudentDto::getHomeAddress)// TODO #93
                .collect(toSet());
    }

    @Override
    public Optional<LocationDto> findByStudentIdAndLocationId(Long studentId, Long locationId) {
        return findLocationsByStudentId(studentId)
                .stream()
                .filter(p -> p.getId().equals(locationId))
                .findFirst();
    }

    @Override
    public Set<SlotDto> findSlotsByStudentId(Long studentId) {
        return service.findById(studentId)
                .stream()
                .map(p ->  entityToDto.convert(p))
                .map(StudentDto::getSlots)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<SlotDto> findByStudentIdAndSlotId(Long studentId, Long slotId) {
        return findSlotsByStudentId(studentId)
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public void deleteByStudentIdAndCardId(long studentId, long cardId) {
        // Step 1 - Get Slot and Student
        StudentDto studentDto =  entityToDto.convert(service.findById(studentId).get());
        Optional<CardDto> cardDtoOptionalToBeRemoved = studentDto
                .getCards()
                .stream()
                .filter(p -> p.getId().equals(cardId))
                .findFirst();

        // Step 2 - remove link student -> card
        studentDto.getCards().remove(cardDtoOptionalToBeRemoved.get());

        // Step 3 - remove link course -> slot
        cardDtoOptionalToBeRemoved.get().setStudent(null);;

        // Step 4 - persist on database
        service.save(dtoToEntity.convert(studentDto));
    }

    @Override
    public void deleteByStudentIdAndLocationId(long studentId, long locationId) {
        // Step 1 - Get Student and Location
        StudentDto studentDto =  entityToDto.convert(service.findById(studentId).get());
        LocationDto locationDtoToBeRemoved = studentDto
                .getHomeAddress();
//                .stream()
 //               .filter(p -> p.getId().equals(locationId))
 //               .findFirst();

        // Step 2 - remove link student -> location
        studentDto.getCards().remove(locationDtoToBeRemoved);

        // Step 3 - remove link location -> student
        //n/a

        // Step 4 - persist on database
        service.save(dtoToEntity.convert(studentDto));
    }

    @Override
    public void deleteByStudentIdAndSlotId(long studentId, long slotId) {
        // Step 1 - Get Student and Slot
        StudentDto studentDto =  entityToDto.convert(service.findById(studentId).get());
        Optional<SlotDto> slotDtoOptionalToBeRemoved = studentDto
                .getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();

        // Step 2 - remove link student -> slot
        studentDto.getCards().remove(slotDtoOptionalToBeRemoved.get());

        // Step 3 - remove link slot -> student
        slotDtoOptionalToBeRemoved.get().getStudents().remove(studentDto);;

        // Step 4 - persist on database
        service.save(dtoToEntity.convert(studentDto));
    }
}

