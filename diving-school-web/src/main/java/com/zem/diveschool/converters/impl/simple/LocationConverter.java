package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter extends SchoolSimpleConverter<LocationDto, Location> {

    public LocationConverter() {
        super(LocationConverter::convertToEntity, LocationConverter::convertToDto);
    }

    @Synchronized
    private static LocationDto convertToDto(Location entity) {
        return LocationDto.builder()
                .id(entity.getId())
                .streetAddress(entity.getStreetAddress())
                .postalCode(entity.getPostalCode())
                .city(entity.getCity())
                .stateProvince(entity.getStateProvince())
                .country(entity.getCountry())
                .build();
    }

    @Synchronized
    private static Location convertToEntity(LocationDto dto) {
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
