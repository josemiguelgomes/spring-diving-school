package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class SlotDtoToSlot extends ConvertObject<SlotDto, Slot> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Synchronized
    @Nullable
    @Override
    public Slot convert(SlotDto dto) {
        return Slot.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .startDate(dto.getSubmissionStartDateConverted(TimeZone.getDefault().toString()))
                .endDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()))
                .status(dto.getStatus())
                .build();
    }
}
