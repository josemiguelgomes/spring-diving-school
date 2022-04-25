package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.Card;
import com.zem.zemdivingschool.persistence.repositories.CardRepository;
import com.zem.zemdivingschool.persistence.services.CardService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "springdatajpa"})
public class CardSDJpaService extends AbstractSDJpaService<Card, Long, CardRepository>
                              implements CardService {


    protected CardSDJpaService(CardRepository cardRepository) {
        super(cardRepository);
    }

}
