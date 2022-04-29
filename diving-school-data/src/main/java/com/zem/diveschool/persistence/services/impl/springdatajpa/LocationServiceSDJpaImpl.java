package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.repositories.LocationRepository;
import com.zem.diveschool.persistence.services.LocationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "springdatajpa"})
public class LocationServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Location, Long, LocationRepository>
        implements LocationService {

    protected LocationServiceSDJpaImpl(LocationRepository locationRepository) {
        super(locationRepository);
    }
}
