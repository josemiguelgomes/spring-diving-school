package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.services.InstructorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorDtoServiceImpl extends AbstractDtoServiceImpl<InstructorDto, Long, Instructor, InstructorService>
                                implements InstructorDtoService {

    @Override
    public Set<InstructorDto> findAll() {
        return super.findAll();
    }

    @Override
    public InstructorDto findById(Long id) {
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
        InstructorDto instructorDto = entityToDto.convert(service.findById(instructorId));
        return instructorDto.getSlots();
    }

    @Override
    public Optional<SlotDto> findByInstructorIdAndSlotId(Long instructorId, Long slotId) {
        InstructorDto instructorDto = entityToDto.convert(service.findById(instructorId));
        return instructorDto.getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public Set<LocationDto> findLocationsByInstructorId(Long instructorId) {
        InstructorDto instructorDto = entityToDto.convert(service.findById(instructorId));
        // TODO #93
        LocationDto locationDto = instructorDto.getHomeAddress();
        Set<LocationDto> locationsDto = new HashSet<>();
        locationsDto.add(locationDto);
        return locationsDto;
    }

    @Override
    public Optional<LocationDto> findByInstructorIdAndLocationId(Long instructorId, Long locationId) {
        InstructorDto instructorDto = entityToDto.convert(service.findById(instructorId));
        // TODO #93
        return Optional.of(instructorDto.getHomeAddress())
                .stream()
                .filter(p -> p.getId().equals(locationId))
                .findFirst();
    }
}

