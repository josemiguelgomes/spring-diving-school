package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlotLanguageDto extends GenericDto<SlotLanguageDto> {
    private Long id;
    private Language language;
}
