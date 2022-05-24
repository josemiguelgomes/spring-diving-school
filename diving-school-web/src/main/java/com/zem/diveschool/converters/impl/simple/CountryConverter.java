package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.CountryDto;
import com.zem.diveschool.persistence.model.Country;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter extends SchoolSimpleConverter<CountryDto, Country> {

    public CountryConverter() {
        super(CountryConverter::convertToEntity, CountryConverter::convertToDto);
    }

    @Synchronized
    private static CountryDto convertToDto(Country entity) {
        return CountryDto.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .isoCode(entity.getIsoCode())
                .build();
    }

    @Synchronized
    private static Country convertToEntity(CountryDto dto) {
        return Country.builder()
                .id(dto.getId())
                .country(dto.getCountry())
                .isocode(dto.getIsoCode())
                .build();
    }

}
