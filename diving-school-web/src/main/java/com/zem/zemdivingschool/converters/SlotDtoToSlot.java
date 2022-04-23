package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.SlotDto;
import com.zem.zemdivingschool.persistence.model.*;
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
