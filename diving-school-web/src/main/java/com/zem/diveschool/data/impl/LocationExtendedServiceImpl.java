package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.LocationExtendedService;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.services.LocationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationExtendedServiceImpl extends AbstractExtendedServiceImpl<Location, Long, LocationService>
                                implements LocationExtendedService {

    @Override
    public Set<Location> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Location save(Location object) {
        return super.save(object);
    }

    @Override
    public void delete(Location object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends Location> List<S> saveAll(@NotNull Iterable<S> entities) {
        return super.saveAll(entities);
    }
}

