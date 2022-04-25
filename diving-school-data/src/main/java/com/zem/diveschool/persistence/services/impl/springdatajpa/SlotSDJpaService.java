package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.repositories.SlotRepository;
import com.zem.diveschool.persistence.services.SlotService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "springdatajpa"})
public class SlotSDJpaService extends AbstractSDJpaService<Slot, Long, SlotRepository>
        implements SlotService {

    protected SlotSDJpaService(SlotRepository slotRepository) {
        super(slotRepository);
    }
}
