package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.repositories.LocationRepository;
import com.zem.diveschool.persistence.services.LocationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "springdatajpa"})
public class LocationSDJpaService extends AbstractSDJpaService<Location, Long, LocationRepository>
        implements LocationService {

    protected LocationSDJpaService(LocationRepository locationRepository) {
        super(locationRepository);
    }
}
