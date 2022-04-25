package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.Instructor;
import com.zem.zemdivingschool.persistence.repositories.InstructorRepository;
import com.zem.zemdivingschool.persistence.services.InstructorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile({"default", "springdatajpa"})
public class InstructorSDJpaService extends AbstractSDJpaService<Instructor, Long, InstructorRepository>
        implements InstructorService {

    protected InstructorSDJpaService(InstructorRepository instructorRepository) {
        super(instructorRepository);
    }

    @Override
    public Optional<Instructor> findByFirstName(String firstName) {
        return super.getObjectRepository().findByFirstName(firstName);
    }
}
