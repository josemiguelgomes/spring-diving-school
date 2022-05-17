package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CardExtendedService;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.services.CardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardExtendedServiceImpl extends AbstractExtendedServiceImpl<Card, Long, CardService>
        implements CardExtendedService {

    @Override
    public Set<Card> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Card> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Card save(Card object) {
        return super.save(object);
    }

    @Override
    public void delete(Card object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends Card> List<S> saveAll(@NotNull Iterable<S> entities) {
        return super.saveAll(entities);
    }
}

