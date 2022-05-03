package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageToSlotLanguageDtoImpl extends ConvertObject<SlotLanguage, SlotLanguageDto> {
    @Synchronized
    @Nullable
    @Override
    public SlotLanguageDto convert(SlotLanguage entity) {
        if (entity == null) {
            return null;
        }
        return SlotLanguageDto.builder()
                .id(entity.getId())
                .language(entity.getLanguage())
                .build();
    }
}
