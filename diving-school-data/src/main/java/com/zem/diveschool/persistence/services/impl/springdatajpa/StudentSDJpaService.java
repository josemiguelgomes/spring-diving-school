package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.repositories.StudentRepository;
import com.zem.diveschool.persistence.services.StudentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile({"default", "springdatajpa"})
public class StudentSDJpaService extends AbstractSDJpaService<Student, Long, StudentRepository>
        implements StudentService {

    protected StudentSDJpaService(StudentRepository studentRepository) {
        super(studentRepository);
    }

    @Override
    public Optional<Student> findByFirstName(String firstName) {
        return super.getObjectRepository().findByFirstName(firstName);
    }
}
