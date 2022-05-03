package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.LocationDtoService;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.services.LocationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
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
}

