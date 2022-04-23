package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.SlotLanguageDto;
import com.zem.zemdivingschool.persistence.model.SlotLanguage;
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
