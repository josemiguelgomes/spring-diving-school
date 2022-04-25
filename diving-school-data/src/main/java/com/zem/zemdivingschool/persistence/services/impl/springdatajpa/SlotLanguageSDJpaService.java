package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.SlotLanguage;
import com.zem.zemdivingschool.persistence.repositories.SlotLanguageRepository;
import com.zem.zemdivingschool.persistence.services.SlotLanguageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "springdatajpa"})
public class SlotLanguageSDJpaService extends AbstractSDJpaService<SlotLanguage, Long, SlotLanguageRepository>
        implements SlotLanguageService {

    protected SlotLanguageSDJpaService(SlotLanguageRepository slotLanguageRepository) {
        super(slotLanguageRepository);
    }

}
