package com.zem.zemdivingschool.persistence.services.impl.map;

import com.zem.zemdivingschool.persistence.model.Student;
import com.zem.zemdivingschool.persistence.services.LocationService;
import com.zem.zemdivingschool.persistence.services.StudentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class StudentMapService extends AbstractMapService<Student, Long>
        implements StudentService {

    LocationService locationService;

    public StudentMapService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    @Transactional
    public Set<Student> findAll() {
        return super.findAll();
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    @Transactional
    public void delete(Student object) {
        super.delete(object);
    }
    @Override
    @Transactional
    public Student save(Student object) {
        if (object == null) {
            return null;
        }


        //
        // HomeAddress
        //
        if (object.getHomeAddress() == null) {
            throw new RuntimeException("Home Address is required on saving a Student");
        }

        if (object.getHomeAddress().getId() == null) {
            object.setHomeAddress(locationService.save(object.getHomeAddress()));
        }

        // Cards
        // TODO
        /*       if (object.getCards() != null) {
            object.getCards().forEach(card -> {
               if (card.getId() == null) {
               }
            });
        }
*/
        // Slots
        // TODO

  /*      if (object.getSlots() != null) {

        }
*/
        return super.save(object);
    }
    @Override
    @Transactional
    public Student findById(Long id) {
        return super.findById(id);
    }
}
