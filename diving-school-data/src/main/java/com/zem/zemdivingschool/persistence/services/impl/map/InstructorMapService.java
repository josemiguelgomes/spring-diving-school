package com.zem.zemdivingschool.persistence.services.impl.map;

import com.zem.zemdivingschool.persistence.model.Instructor;
import com.zem.zemdivingschool.persistence.services.InstructorService;
import com.zem.zemdivingschool.persistence.services.LocationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class InstructorMapService extends AbstractMapService<Instructor, Long>
        implements InstructorService {
    LocationService locationService;

    public InstructorMapService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Override
    @Transactional
    public Set<Instructor> findAll() {
        return super.findAll();
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    @Transactional
    public Instructor save(Instructor object) {
        if (object == null) {
            return null;
        }

        //
        // Home Address
        //
        if (object.getHomeAddress() == null) {
            throw new RuntimeException("Home Address is required on saving a Instructor");
        }

        if (object.getHomeAddress().getId() == null) {
            object.setHomeAddress(locationService.save(object.getHomeAddress()));
        }

        //
        return super.save(object);
    }

    @Override
    @Transactional
    public void delete(Instructor object) {
        super.delete(object);
    }

    @Override
    @Transactional
    public Instructor findById(Long id) {
        return super.findById(id);
    }
}
