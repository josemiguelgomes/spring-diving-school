package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.repositories.CardRepository;
import com.zem.diveschool.persistence.services.CardService;
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
