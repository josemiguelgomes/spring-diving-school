package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.Location;
import com.zem.zemdivingschool.persistence.repositories.LocationRepository;
import com.zem.zemdivingschool.persistence.services.LocationService;
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
