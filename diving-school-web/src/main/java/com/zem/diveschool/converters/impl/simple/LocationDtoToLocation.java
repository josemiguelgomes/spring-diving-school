package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoToLocation extends ConvertObject<LocationDto, Location> {
    @Nullable
    @Override
    public Location convert(LocationDto dto) {
        return Location.builder()
                .id(dto.getId())
                .streetAddress(dto.getStreetAddress())
                .postalCode(dto.getPostalCode())
                .city(dto.getCity())
                .stateProvince(dto.getStateProvince())
                .country(dto.getCountry())
                .build();
    }
}
