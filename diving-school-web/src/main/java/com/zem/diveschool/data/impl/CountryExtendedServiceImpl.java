package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CountryExtendedService;
import com.zem.diveschool.persistence.model.Country;
import com.zem.diveschool.persistence.services.CountryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryExtendedServiceImpl extends AbstractExtendedServiceImpl<Country, Long, CountryService>
        implements CountryExtendedService {

    @Override
    public Set<Country> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Country save(Country object) {
        return super.save(object);
    }

    @Override
    public void delete(Country object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends Country> List<S> saveAll(@NotNull Iterable<S> entities) {
        return super.saveAll(entities);
    }
}

