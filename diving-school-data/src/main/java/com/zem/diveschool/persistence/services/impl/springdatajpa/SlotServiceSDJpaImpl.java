package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.repositories.SlotRepository;
import com.zem.diveschool.persistence.services.SlotService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "springdatajpa"})
public class SlotServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Slot, Long, SlotRepository>
        implements SlotService {

    protected SlotServiceSDJpaImpl(SlotRepository slotRepository) {
        super(slotRepository);
    }

    @Override
    public Set<Slot> findByStudentID(Long id) {
        return super.findAll()
                .stream()
                .map(Slot::getStudents)
                .flatMap(Collection::stream)
                .filter(p -> p.getId().equals(id))
                .map(Student::getSlots)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
