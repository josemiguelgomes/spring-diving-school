package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.*;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageDtoToSlotLanguage extends ConvertObject<SlotLanguageDto, SlotLanguage> {
    @Synchronized
    @Nullable
    @Override
    public SlotLanguage convert(SlotLanguageDto dto) {
        return SlotLanguage.builder()
                .id(dto.getId())
                .language(dto.getLanguage())
                .build();
    }
}
