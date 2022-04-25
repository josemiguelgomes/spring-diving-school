package com.zem.diveschool.persistence.repositories;

import com.zem.diveschool.persistence.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {

    //
}
