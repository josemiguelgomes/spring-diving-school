package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto extends GenericDto<LocationDto> {
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private Country country;

    @Builder
    public LocationDto(Long id, String streetAddress, String postalCode, String city, String stateProvince,
                       Country country) {
        super(id);
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }
}
