package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Slot;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class SlotConverter extends SchoolSimpleConverter<SlotDto, Slot> {

    public SlotConverter() {
        super(SlotConverter::convertToEntity, SlotConverter::convertToDto);
    }

    @Synchronized
    private static SlotDto convertToDto(Slot entity) {
        return SlotDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .startDate(dateFormat.format(entity.getStartDate()))
                .endDate(dateFormat.format(entity.getEndDate()))
                .status(entity.getStatus())
                .build();
    }

    @Synchronized
    private static Slot convertToEntity(SlotDto dto) {
        return Slot.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .startDate(dto.getSubmissionStartDateConverted(TimeZone.getDefault().toString()))
                .endDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()))
                .status(dto.getStatus())
                .build();
    }
}
