package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.repositories.SlotLanguageRepository;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "springdatajpa"})
public class SlotLanguageServiceSDJpaImpl extends AbstractServiceSDJpaImpl<SlotLanguage, Long, SlotLanguageRepository>
        implements SlotLanguageService {

    protected SlotLanguageServiceSDJpaImpl(SlotLanguageRepository slotLanguageRepository) {
        super(slotLanguageRepository);
    }

}
