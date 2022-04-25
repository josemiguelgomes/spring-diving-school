package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.Slot;
import com.zem.zemdivingschool.persistence.repositories.SlotRepository;
import com.zem.zemdivingschool.persistence.services.SlotService;
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
