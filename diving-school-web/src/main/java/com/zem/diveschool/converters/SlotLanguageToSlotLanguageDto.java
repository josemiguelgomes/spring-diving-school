package com.zem.diveschool.converters;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageToSlotLanguageDto implements Converter<SlotLanguage, SlotLanguageDto> {
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
