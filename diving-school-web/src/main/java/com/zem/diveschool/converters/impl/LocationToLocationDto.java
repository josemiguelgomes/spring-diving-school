package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationDto extends ConvertObject<Location, LocationDto> {
    @Nullable
    @Override
    public LocationDto convert(Location entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final LocationDto locationDto = new LocationDto();

        locationDto.setId(entity.getId());
        locationDto.setStreetAddress(entity.getStreetAddress());
        locationDto.setPostalCode(entity.getPostalCode());
        locationDto.setCity(entity.getCity());
        locationDto.setStateProvince(entity.getStateProvince());
        locationDto.setCountry(entity.getCountry());

        return locationDto;
    }
}
