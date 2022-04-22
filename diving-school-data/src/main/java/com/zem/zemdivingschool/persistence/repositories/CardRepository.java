package com.zem.zemdivingschool.persistence.repositories;

import com.zem.zemdivingschool.persistence.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
    //
}
