package com.zem.zemdivingschool.persistence.services.impl.map;

import com.zem.zemdivingschool.persistence.model.Location;
import com.zem.zemdivingschool.persistence.services.LocationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class LocationMapService extends AbstractMapService<Location, Long>
                                implements LocationService {

    @Override
    @Transactional
    public Set<Location> findAll() {
        return super.findAll();
    }
    @Override
    @Transactional
    public Location findById(Long id) {
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
