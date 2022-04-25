package com.zem.diveschool.converters;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class SlotToSlotDto implements Converter<Slot, SlotDto> {
    @Nullable
    @Override
    public SlotDto convert(Slot entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final SlotDto slotDto = new SlotDto();

        slotDto.setId(entity.getId());
        slotDto.setTitle(entity.getTitle());
        slotDto.setSubmissionStartDate(entity.getStartDate(), TimeZone.getDefault().toString());
        slotDto.setSubmissionEndDate(entity.getEndDate(), TimeZone.getDefault().toString());
        slotDto.setStatus(entity.getStatus());

        return slotDto;
    }
}
