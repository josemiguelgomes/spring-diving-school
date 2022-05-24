package com.zem.diveschool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDto extends GenericDto<CountryDto> {

    private String country;
    private String isoCode;

    @Builder
    public CountryDto(Long id, String country, String isoCode) {
        super(id);
        this.country = country;
        this.isoCode = isoCode;
    }
}
