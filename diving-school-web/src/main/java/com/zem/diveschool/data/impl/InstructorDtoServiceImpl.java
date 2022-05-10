package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.services.InstructorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InstructorDtoServiceImpl extends AbstractDtoServiceImpl<InstructorDto, Long, Instructor, InstructorService>
                                implements InstructorDtoService {

    @Override
    public Set<InstructorDto> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<InstructorDto> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public InstructorDto save(InstructorDto object) {
       return super.save(object);
    }

    @Override
    public void delete(InstructorDto object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends InstructorDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
       return super.saveAll(dtos);
    }

    @Override
    public Set<SlotDto> findSlotsByInstructorId(Long instructorId) {
        return service.findById(instructorId)
                .stream()
                .map(p -> entityToDto.convert(p))
                .map(InstructorDto::getSlots)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<SlotDto> findByInstructorIdAndSlotId(Long instructorId, Long slotId) {
        return findSlotsByInstructorId(instructorId)
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public Set<LocationDto> findLocationsByInstructorId(Long instructorId) {
        return service.findById(instructorId)
                .stream()
                .map(p -> entityToDto.convert(p))
  //            .map(InstructorDto::getLocations) // TODO #93
                .map(InstructorDto::getHomeAddress) // TODO #93
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<LocationDto> findByInstructorIdAndLocationId(Long instructorId, Long locationId) {
        return findLocationsByInstructorId(instructorId)
                .stream()
                .filter(p -> p.getId().equals(locationId))
                .findFirst();
    }

    @Override
    public void deleteByInstructorIdAndLocationId(Long instructorId, Long locationID) {
        // Step 1 - Get Instructor and Location
        InstructorDto instructorDto =  entityToDto.convert(service.findById(instructorId).get());
        LocationDto locationDtoToBeRemoved = instructorDto
                .getHomeAddress();   // TODO #93
  //              .getLocations()
 //             .stream()
 //             .filter(p -> p.getId().equals(locationID))
//                .findFirst();

        // Step 2 - remove link Instructor -> Location
        instructorDto.getSlots().remove(locationDtoToBeRemoved);

        // Step 3 - remove link Location -> Instructor
        // n/a

        // Step 4 - persist on database
        service.save(dtoToEntity.convert(instructorDto));
    }

    @Override
    public void deleteByInstructorIdAndSlotId(Long instructorId, Long slotId) {
        // Step 1 - Get Instructor and Slot
        InstructorDto instructorDto =  entityToDto.convert(service.findById(instructorId).get());
        Optional<SlotDto> slotDtoOptionalToBeRemoved = instructorDto
                .getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();

        // Step 2 - remove link Instructor -> Location
        instructorDto.getSlots().remove(slotDtoOptionalToBeRemoved.get());

        // Step 3 - remove link Slot -> Instructor
        slotDtoOptionalToBeRemoved.get().getInstructors().remove(instructorDto);

        // Step 4 - persist on database
        service.save(dtoToEntity.convert(instructorDto));
    }

}

