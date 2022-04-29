package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.StudentService;
import com.zem.diveschool.persistence.services.LocationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class StudentServiceMapImpl extends AbstractServiceMapsImpl<Student, Long>
                               implements StudentService {

    LocationService locationService;

    public StudentServiceMapImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    @Transactional
    public Set<Student> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional
    public Student findById(Long id) {
        return super.findById(id);
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
    public void delete(Student object) {
        super.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Student> findByFirstName(String firstName) {
        for(Student student : map.values()) {
            if (student.getFirstName().equals(firstName)) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}
