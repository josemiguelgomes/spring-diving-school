package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.LocationDto;
import com.zem.zemdivingschool.persistence.model.Location;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoToLocation implements Converter<LocationDto, Location> {
    @Nullable
    @Override
    public Location convert(LocationDto dto) {
 /*
        if (dto == null) {
            return null;
        }
  */
        final Location location = new Location();
        location.setId(dto.getId());
        location.setStreetAddress(dto.getStreetAddress());
        location.setPostalCode(dto.getPostalCode());
        location.setCity(dto.getCity());
        location.setStateProvince(dto.getStateProvince());
        location.setCountry(dto.getCountry());

        return location;
    }
}
