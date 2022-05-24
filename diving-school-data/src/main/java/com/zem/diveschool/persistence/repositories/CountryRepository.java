package com.zem.diveschool.persistence.repositories;

import com.zem.diveschool.persistence.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

    //
}
