package com.zem.diveschool.formatters;

import com.zem.diveschool.converters.impl.simple.CountryConverter;
import com.zem.diveschool.data.CountryExtendedService;
import com.zem.diveschool.dto.CountryDto;
import com.zem.diveschool.persistence.model.Country;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class CountryFormatter implements Formatter<CountryDto> {

        private final CountryExtendedService countryExtendedService;
        private final CountryConverter countryConverter;

        public CountryFormatter(CountryExtendedService countryExtendedService,
                                CountryConverter countryConverter) {
                this.countryExtendedService = countryExtendedService;
                this.countryConverter = countryConverter;
        }

        @Override
        public String print(CountryDto countryDto, Locale locale) {
                return countryDto.getCountry();
        }

        @Override
        public CountryDto parse(String text, Locale locale) throws ParseException {
                Collection<Country> findCountries = countryExtendedService.findAll();

                for (Country country : findCountries) {
                        if (country.getCountry().equals(text)) {
                                return countryConverter.convertFromEntity(country);
                        }
                }

                throw new ParseException("Country not found: " + text, 0);
        }

}
