package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class SlotDtoToSlot extends ConvertObject<SlotDto, Slot> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
            try {
                slot.setStartDate(sdf.parse("0001-01-01"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            // throw new RuntimeException(e);   // TODO: fix this exception and also the callers
        }
        try {
            slot.setEndDate(dto.getSubmissionEndDateConverted(TimeZone.getDefault().toString()));
        } catch (ParseException e) {
            try {
                slot.setStartDate(sdf.parse("0001-01-01"));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            // throw new RuntimeException(e);   // TODO: fix this exception and also the callers
        }
        slot.setStatus(dto.getStatus());

        return slot;
    }
}
