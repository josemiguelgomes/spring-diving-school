package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.LocationDtoService;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.services.LocationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationDtoServiceImpl extends AbstractDtoServiceImpl<LocationDto, Long, Location, LocationService>
                                implements LocationDtoService {

    @Override
    public Set<LocationDto> findAll() {
        return super.findAll();
    }

    @Override
    public LocationDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public LocationDto save(LocationDto object) {
        return super.save(object);
    }

    @Override
    public void delete(LocationDto object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends LocationDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
        return super.saveAll(dtos);
    }

    @Override
    @Transactional
    public Set<LocationDto> findByInstructorId(Long instructorId) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<LocationDto> findByInstructionAndLocationId(Long instructorId, Long locationId) {
        // TODO
        return Optional.empty();
    }

    @Override
    @Transactional
    public Set<LocationDto> findBySlotId(Long slotId) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<LocationDto> findBySlotIdAndLocationID(Long slotId, Long locationId) {
        // TODO
        return Optional.empty();
    }

    @Override
    @Transactional
    public Set<LocationDto> findByStudentId(Long studentId) {
        return null;
    }

    @Override
    @Transactional
    public Optional<LocationDto> findByStudentIdAndLocationId(Long studentId, Long locationId) {
        return Optional.empty();
    }
}

