package com.zem.diveschool.converters;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.TimeZone;

@Component
public class SlotDtoToSlot implements Converter<SlotDto, Slot> {
    @Nullable
    @Override
    public Slot convert(SlotDto dto) {
 /*
        if (dto == null) {
            return null;
        }
  */
        final Slot slot = new Slot();
        slot.setId(dto.getId());

        slot.setTitle(dto.getTitle());
        try {
            slot.setStartDate(dto.getSubmissionStartDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            slot.setEndDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        slot.setStatus(dto.getStatus());

        return slot;
    }
}
