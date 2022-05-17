package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.StudentExtendedService;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class StudentExtendedServiceImpl extends AbstractExtendedServiceImpl<Student, Long, StudentService>
                                implements StudentExtendedService {

    @Override
    public Set<Student> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Student save(Student object) {
       return super.save(object);
    }

    @Override
    public void delete(Student object) {
       super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public <S extends Student> List<S> saveAll(@NotNull Iterable<S> entities) {
       return super.saveAll(entities);
    }

    @Override
    public Set<Card> findCardsByStudentId(Long studentId) {
        return service.findById(studentId)
                .stream()
                .map(Student::getCards)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<Card> findByStudentIdAndCardId(Long studentId, Long cardId) {
        return findCardsByStudentId(studentId)
                .stream()
                .filter(p -> p.getId().equals(cardId))
                .findFirst();
    }

    @Override
    public Set<Location> findLocationsByStudentId(Long studentId) {
        return service.findById(studentId)
                .stream()
//              .map(p -> p.getLocations())// TODO #93
//              .flatMap(Collection::stream)
                .map(Student::getHomeAddress)// TODO #93
                .collect(toSet());
    }

    @Override
    public Optional<Location> findByStudentIdAndLocationId(Long studentId, Long locationId) {
        return findLocationsByStudentId(studentId)
                .stream()
                .filter(p -> p.getId().equals(locationId))
                .findFirst();
    }

    @Override
    public Set<Slot> findSlotsByStudentId(Long studentId) {
        return service.findById(studentId)
                .stream()
                .map(Student::getSlots)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    @Override
    public Optional<Slot> findByStudentIdAndSlotId(Long studentId, Long slotId) {
        return findSlotsByStudentId(studentId)
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();
    }

    @Override
    public void deleteByStudentIdAndCardId(long studentId, long cardId) {
        // Step 1 - Get Slot and Student
        Optional<Student> studentOptional = service.findById(studentId);
        Optional<Card> cardOptionalToBeRemoved = studentOptional.get()
                .getCards()
                .stream()
                .filter(p -> p.getId().equals(cardId))
                .findFirst();

        // Step 2 - remove link student -> card
        studentOptional.get().getCards().remove(cardOptionalToBeRemoved.get());

        // Step 3 - remove link course -> slot
        cardOptionalToBeRemoved.get().setStudent(null);;

        // Step 4 - persist on database
        service.save(studentOptional.get());
    }

    @Override
    public void deleteByStudentIdAndLocationId(long studentId, long locationId) {
        // Step 1 - Get Student and Location
        Optional<Student> studentOptional = service.findById(studentId);
        Location locationToBeRemoved = studentOptional.get()
                .getHomeAddress();
//                .stream()
 //               .filter(p -> p.getId().equals(locationId))
 //               .findFirst();

        // Step 2 - remove link student -> location
        studentOptional.get().getCards().remove(locationToBeRemoved);

        // Step 3 - remove link location -> student
        //n/a

        // Step 4 - persist on database
        service.save(studentOptional.get());
    }

    @Override
    public void deleteByStudentIdAndSlotId(long studentId, long slotId) {
        // Step 1 - Get Student and Slot
        Optional<Student> studentOptional = service.findById(studentId);
        Optional<Slot> slotOptionalToBeRemoved = studentOptional.get()
                .getSlots()
                .stream()
                .filter(p -> p.getId().equals(slotId))
                .findFirst();

        // Step 2 - remove link student -> slot
        studentOptional.get().getCards().remove(slotOptionalToBeRemoved.get());

        // Step 3 - remove link slot -> student
        slotOptionalToBeRemoved.get().getStudents().remove(studentOptional.get());;

        // Step 4 - persist on database
        service.save(studentOptional.get());
    }

    @Override
    public void saveImageFile(Long studentId, MultipartFile file) {
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            super.saveImageFile(studentId, byteObjects);

        } catch (IOException e) {
            //todo handle better
            e.printStackTrace();
        }
    }

    public Set<Student> findAllByLastNameLike(String lastName) {
        return service.findAllByLastNameLike(lastName);
    }
}

