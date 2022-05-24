package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Country;
import com.zem.diveschool.persistence.repositories.CardRepository;
import com.zem.diveschool.persistence.repositories.CountryRepository;
import com.zem.diveschool.persistence.repositories.StudentRepository;
import com.zem.diveschool.persistence.services.CardService;
import com.zem.diveschool.persistence.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Profile({"default", "springdatajpa"})
public class CountryServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Country, Long, CountryRepository>
                              implements CountryService {

    CountryRepository countryRepository;

    protected CountryServiceSDJpaImpl(CountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public Country save(Country country) {
        return countryRepository.save(country);
    }

}
