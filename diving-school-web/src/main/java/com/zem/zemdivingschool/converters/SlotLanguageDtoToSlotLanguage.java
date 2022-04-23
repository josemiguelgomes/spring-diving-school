package com.zem.zemdivingschool.converters;

import com.zem.zemdivingschool.dto.SlotLanguageDto;
import com.zem.zemdivingschool.persistence.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageDtoToSlotLanguage implements Converter<SlotLanguageDto, SlotLanguage> {
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
