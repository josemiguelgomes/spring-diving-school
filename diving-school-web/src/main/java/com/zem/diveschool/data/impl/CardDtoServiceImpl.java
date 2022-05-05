package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.services.CardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CardDtoServiceImpl extends AbstractDtoServiceImpl<CardDto, Long, Card, CardService>
        implements CardDtoService {

    @Override
    public Set<CardDto> findAll() {
        return super.findAll();
    }

    @Override
    public CardDto findById(Long id) {
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

    @Override
    @Transactional
    public Set<CardDto> findByStudentID(Long id) {
        return entityToDto.convert(service.findByStudentID(id));
    }

    @Override
    @Transactional
    public Optional<CardDto> findByStudentIdAndCardId(Long studentId, Long cardId) {
        return Optional.empty();
        //TODO
        // return entityToDto.convert(service.findByStudentIdAndCardId(studentId, cardId));
    }
}

