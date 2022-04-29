package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.repositories.InstructorRepository;
import com.zem.diveschool.persistence.services.InstructorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile({"default", "springdatajpa"})
public class InstructorServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Instructor, Long, InstructorRepository>
        implements InstructorService {

    protected InstructorServiceSDJpaImpl(InstructorRepository instructorRepository) {
        super(instructorRepository);
    }

    @Override
    public Optional<Instructor> findByFirstName(String firstName) {
        return super.getObjectRepository().findByFirstName(firstName);
    }
}
