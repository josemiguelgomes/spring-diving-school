package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlotLanguageDto extends GenericDto<SlotLanguageDto> {
    private Language language;

    @Builder
    public SlotLanguageDto(Long id, Language language) {
        super(id);
        this.language = language;
    }
}
