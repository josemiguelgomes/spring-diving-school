package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageToSlotLanguageDto extends ConvertObject<SlotLanguage, SlotLanguageDto> {
    @Nullable
    @Override
    public SlotLanguageDto convert(SlotLanguage entity) {
        return SlotLanguageDto.builder()
                .id(entity.getId())
                .language(entity.getLanguage())
                .build();
    }
}
