package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.InstructorExtendedService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.services.InstructorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InstructorExtendedServiceImpl extends AbstractExtendedServiceImpl<Instructor, Long, InstructorService>
                                implements InstructorExtendedService {

    @Override
    public Set<Instructor> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Instructor save(Instructor object) {
       return super.save(object);
    }

    @Override
    public void delete(Instructor object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends Instructor> List<S> saveAll(@NotNull Iterable<S> entities) {
       return super.saveAll(entities);
    }

    @Override
    public Set<Slot> findSlotsByInstructorId(Long instructorId) {
        return service.findById(instructorId)
                .stream()
                .map(Instructor::getSlots)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Slot> findByInstructorIdAndSlotId(Long instructorId, Long slotId) {
        return findSlotsByInstructorId(instructorId)
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public Set<Location> findLocationsByInstructorId(Long instructorId) {
        return service.findById(instructorId)
                .stream()
  //            .map(Instructor::getLocations) // TODO #93
                .map(Instructor::getHomeAddress) // TODO #93
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Location> findByInstructorIdAndLocationId(Long instructorId, Long locationId) {
        return findLocationsByInstructorId(instructorId)
                .stream()
                .filter(p -> p.getId().equals(locationId))
                .findFirst();
    }

    @Override
    public void deleteByInstructorIdAndLocationId(Long instructorId, Long locationID) {
        // Step 1 - Get Instructor and Location
        Optional<Instructor> instructorOptional = service.findById(instructorId);
        Location locationToBeRemoved = instructorOptional.get()
                .getHomeAddress();   // TODO #93
  //              .getLocations()
 //             .stream()
 //             .filter(p -> p.getId().equals(locationID))
//                .findFirst();

        // Step 2 - remove link Instructor -> Location
        instructorOptional.get().getSlots().remove(locationToBeRemoved);

        // Step 3 - remove link Location -> Instructor
        // n/a

        // Step 4 - persist on database
        service.save(instructorOptional.get());
    }

    @Override
    public void deleteByInstructorIdAndSlotId(Long instructorId, Long slotId) {
        // Step 1 - Get Instructor and Slot
        Optional<Instructor> instructorOptional = service.findById(instructorId);
        Optional<Slot> slotDtoOptionalToBeRemoved = instructorOptional.get()
                .getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();

        // Step 2 - remove link Instructor -> Location
        instructorOptional.get().getSlots().remove(slotDtoOptionalToBeRemoved.get());

        // Step 3 - remove link Slot -> Instructor
        slotDtoOptionalToBeRemoved.get().getInstructors().remove(instructorOptional.get());

        // Step 4 - persist on database
        service.save(instructorOptional.get());
    }

}

