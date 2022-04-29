package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.repositories.SlotRepository;
import com.zem.diveschool.persistence.services.SlotService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default", "springdatajpa"})
public class SlotServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Slot, Long, SlotRepository>
        implements SlotService {

    protected SlotServiceSDJpaImpl(SlotRepository slotRepository) {
        super(slotRepository);
    }

    @Override
    public Set<Slot> findByStudentID(Long id) {
        Set<Slot> slots = new HashSet<>();

        for (Slot slot : super.findAll()) {
            for(Student student : slot.getStudents()) {
                if (student.getId().equals(id)) {
                    slots.add(slot);
                }
            }
        }

        return slots;
    }
}
