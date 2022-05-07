package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.services.LocationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Profile({"default", "map"})
@Service
public class LocationServiceMapImpl extends AbstractServiceMapsImpl<Location, Long>
                                implements LocationService {

    @Override
    @Transactional
    public Set<Location> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional
    public Optional<Location> findById(Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public Location save(Location object) {
        if (object == null) {
            return null;
        }

        return super.save(object);
    }
    @Override
    @Transactional
    public void delete(Location object) {
        super.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
