package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.repositories.CardRepository;
import com.zem.diveschool.persistence.services.CardService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default", "springdatajpa"})
public class CardServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Card, Long, CardRepository>
                              implements CardService {

    protected CardServiceSDJpaImpl(CardRepository cardRepository) {
        super(cardRepository);
    }

    @Override
    public Set<Card> findByStudentID(Long id) {
        Set<Card> cards = new HashSet<>();

        for (Card card : super.findAll()) {
            if (card.getStudent().getId().equals(id)) {
                cards.add(card);
            }
        }

        return cards;
    }


}
