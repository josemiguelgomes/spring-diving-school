package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationDtoImpl extends ConvertObject<Location, LocationDto> {
    @Synchronized
    @Nullable
    @Override
    public LocationDto convert(Location entity) {
        if (entity == null) {
            return null;
        }
        return LocationDto.builder()
                .id(entity.getId())
                .streetAddress(entity.getStreetAddress())
                .postalCode(entity.getPostalCode())
                .city(entity.getCity())
                .stateProvince(entity.getStateProvince())
                .country(entity.getCountry())
                .build();
    }
}
