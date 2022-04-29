package com.zem.diveschool.converters.impl;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageToSlotLanguageDto extends ConvertObject<SlotLanguage, SlotLanguageDto> {
    @Nullable
    @Override
    public SlotLanguageDto convert(SlotLanguage entity) {
/*
        if (entity == null) {
            return null;
        }
*/
        final SlotLanguageDto slotLanguageDto = new SlotLanguageDto();

        slotLanguageDto.setId(entity.getId());
        slotLanguageDto.setLanguage(entity.getLanguage());

        return slotLanguageDto;
    }
}
