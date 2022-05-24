package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.model.Country;
import com.zem.diveschool.persistence.services.CountryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class CountryServiceMapImpl extends AbstractServiceMapsImpl<Country, Long>
                            implements CountryService {
    private final CountryService countryService;

    public CountryServiceMapImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    @Transactional
    public Set<Country> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional
    public Optional<Country> findById(Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public Country save(Country object) {
        if (object == null) {
            return null;
        }

        return super.save(object);
    }

    @Override
    @Transactional
    public void delete(Country object) {
        super.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    /*
    @Override
    public Set<Country> findByCountryID(Long id) {
        Set<Country> countries = new HashSet<>();

        for (Country country : super.findAll()) {
            if (country.getId().equals(id)) {
                countries.add(country);
            }
        }

        return countries;
    }
    */
}
