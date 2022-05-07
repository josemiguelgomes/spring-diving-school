package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.services.CardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardDtoServiceImpl extends AbstractDtoServiceImpl<CardDto, Long, Card, CardService>
        implements CardDtoService {

    @Override
    public Set<CardDto> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<CardDto> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public CardDto save(CardDto object) {
        return super.save(object);
    }

    @Override
    public void delete(CardDto object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends CardDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
        return super.saveAll(dtos);
    }
}

