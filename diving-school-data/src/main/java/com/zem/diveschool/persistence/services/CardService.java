package com.zem.diveschool.persistence.services;

import com.zem.diveschool.persistence.model.Card;

import java.util.Optional;
import java.util.Set;

public interface CardService extends CrudService<Card, Long> {

    //
    Set<Card> findByStudentID(Long id);
}
