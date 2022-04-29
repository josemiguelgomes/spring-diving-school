package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageDtoToSlotLanguage extends ConvertObject<SlotLanguageDto, SlotLanguage> {
    @Nullable
    @Override
    public SlotLanguage convert(SlotLanguageDto dto) {
 /*
        if (dto == null) {
            return null;
        }
  */
        final SlotLanguage slotLanguage = new SlotLanguage();
        slotLanguage.setId(dto.getId());
        slotLanguage.setLanguage(dto.getLanguage());

        return slotLanguage;
    }
}
