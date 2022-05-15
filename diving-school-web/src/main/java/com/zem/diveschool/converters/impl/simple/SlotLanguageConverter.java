package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class SlotLanguageConverter extends SchoolSimpleConverter<SlotLanguageDto, SlotLanguage> {

    public SlotLanguageConverter() {
        super(SlotLanguageConverter::convertToEntity, SlotLanguageConverter::convertToDto);
    }

    @Synchronized
    private static SlotLanguageDto convertToDto(SlotLanguage entity) {
        return SlotLanguageDto.builder()
                .id(entity.getId())
                .language(entity.getLanguage())
                .build();
    }

    @Synchronized
    private static SlotLanguage convertToEntity(SlotLanguageDto dto) {
        return SlotLanguage.builder()
                .id(dto.getId())
                .language(dto.getLanguage())
                .build();
    }
}
