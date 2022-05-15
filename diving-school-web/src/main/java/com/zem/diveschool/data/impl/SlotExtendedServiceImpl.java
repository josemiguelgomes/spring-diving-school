package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.model.*;
import com.zem.diveschool.persistence.services.SlotService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class SlotExtendedServiceImpl extends AbstractExtendedServiceImpl<Slot, Long, SlotService>
                                implements SlotExtendedService {

    @Override
    public Set<Slot> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Slot> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Slot save(Slot object) {
        return super.save(object);
    }

    @Override
    public void delete(Slot object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends Slot> List<S> saveAll(@NotNull Iterable<S> dtos) {
        return super.saveAll(dtos);
    }

    @Override
    public Set<Course> findCoursesBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                //.map(SlotDto::getCourses) // TODO #93
                .map(Slot::getCourse)// TODO #93
                .collect(toSet());
    }

    @Override
    public Optional<Course> findBySlotIdAndCourseId(Long slotId, Long courseId) {
        return findCoursesBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(courseId))
                .findFirst();
    }

    @Override
    public Set<Instructor> findInstructorsBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(Slot::getInstructors)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<Instructor> findBySlotIdAndInstructorId(Long slotId, Long instructorId) {
        return findInstructorsBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(instructorId))
                .findFirst();
    }

    @Override
    public Set<SlotLanguage> findLanguagesBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(Slot::getLanguages)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<SlotLanguage> findBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId) {
        return findLanguagesBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(slotLanguageId))
                .findFirst();
    }

    @Override
    public Set<Student> findStudentsBySlotId(Long slotId) {
        return service.findById(slotId)
                .stream()
                .map(Slot::getStudents)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<Student> findBySlotIdAndStudentId(Long slotId, Long studentId) {
        return findStudentsBySlotId(slotId)
                .stream()
                .filter(p -> p.getId().equals(studentId))
                .findFirst();
    }

    @Override
    public void deleteBySlotIdAndCourseId(Long slotId, Long courseId) {
        // Step 1 - Get Slot and Course
        Optional<Slot> slotOptional = service.findById(slotId);
        Course courseToBeRemoved = slotOptional.get()
                .getCourse();
//                .stream()
  //              .filter(p -> p.getId().equals(slotId))
    //            .findFirst();

        // Step 2 - remove link slot -> course
//        slotDto.getCourses().remove(courseDtoOptionalToBeRemoved.get());
        slotOptional.get().setCourse(null);

        // Step 3 - remove link course -> slot
        courseToBeRemoved.getSlots().remove(slotOptional.get());

        // Step 4 - persist on database
        service.save(slotOptional.get());
    }

    @Override
    public void deleteBySlotIdAndInstructorId(Long slotId, Long instructorId) {
        // Step 1 - Get Slot and Instructor
        Optional<Slot> slotOptional = service.findById(slotId);
        Optional<Instructor> instructorOptionalToBeRemoved = slotOptional.get()
                .getInstructors()
                .stream()
                .filter(p -> p.getId().equals(instructorId))
                .findFirst();

        // Step 2 - remove link slot -> instructor
        slotOptional.get().getInstructors().remove(instructorOptionalToBeRemoved.get());

        // Step 3 - remove link course -> slot
        instructorOptionalToBeRemoved.get().getSlots().remove(slotOptional.get());

        // Step 4 - persist on database
        service.save(slotOptional.get());
    }

    @Override
    public void deleteBySlotIdAndSlotLanguageId(Long slotId, Long slotLanguageId) {
        // Step 1 - Get Slot and Slot Language
        Optional<Slot> slotOptional = service.findById(slotId);
        Optional<SlotLanguage> slotLanguageOptionalToBeRemoved = slotOptional.get()
                .getLanguages()
                .stream()
                .filter(p -> p.getId().equals(slotLanguageId))
                .findFirst();

        // Step 2 - remove link slot -> slot language
        slotOptional.get().getLanguages().remove(slotLanguageOptionalToBeRemoved.get());

        // Step 3 - remove link slot language -> slot
        //n/a

        // Step 4 - persist on database
        service.save(slotOptional.get());
    }

    @Override
    public void deleteBySlotIdAndStudentId(Long slotId, Long studentID) {
        // Step 1 - Get Slot and Student
        Optional<Slot> slotOptional = service.findById(slotId);
        Optional<Student> studentOptionalToBeRemoved = slotOptional.get()
                .getStudents()
                .stream()
                .filter(p -> p.getId().equals(studentID))
                .findFirst();

        // Step 2 - remove link slot -> instructor
        slotOptional.get().getStudents().remove(studentOptionalToBeRemoved.get());

        // Step 3 - remove link course -> slot
        studentOptionalToBeRemoved.get().getSlots().remove(slotOptional.get());

        // Step 4 - persist on database
        service.save(slotOptional.get());
    }

}

