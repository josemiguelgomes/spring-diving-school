package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.repositories.StudentRepository;
import com.zem.diveschool.persistence.services.StudentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "springdatajpa"})
public class StudentServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Student, Long, StudentRepository>
        implements StudentService {

    protected StudentServiceSDJpaImpl(StudentRepository studentRepository) {
        super(studentRepository);
    }

    @Override
    public Optional<Student> findByFirstName(String firstName) {
        return super.getObjectRepository().findByFirstName(firstName);
    }

    @Override
    public void saveImageFile(Long studentId, Byte[] image) {
        Optional<Student> studentOptional = super.findById(studentId);
        if(studentOptional.isPresent()) {
            studentOptional.get().setPhoto(image);
            super.getObjectRepository().save(studentOptional.get());
        }
    }

    @Override
    public Set<Student> findAllByLastNameLike(String lastName) {
        return super.getObjectRepository().findAllByLastNameLike(lastName);
    }
}
