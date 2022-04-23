package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.LocationDto;
import com.zem.zemdivingschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationDto implements Converter<Location, LocationDto> {
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
