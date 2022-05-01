package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class SlotToSlotDto extends ConvertObject<Slot, SlotDto> {
    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Nullable
    @Override
    public SlotDto convert(Slot entity) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().toString()));
        return SlotDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .startDate(dateFormat.format(entity.getStartDate()))
                .endDate(dateFormat.format(entity.getEndDate()))
                .status(entity.getStatus())
                .build();
    }
}
