package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SlotLanguageDto extends GenericDto<SlotLanguageDto> {
    private Language language;
}
