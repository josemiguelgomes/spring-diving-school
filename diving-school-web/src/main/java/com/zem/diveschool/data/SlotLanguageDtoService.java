package com.zem.diveschool.data;

import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.services.CrudService;

import java.util.Optional;
import java.util.Set;

public interface SlotLanguageDtoService extends CrudService<SlotLanguageDto, Long> {

    Set<SlotLanguageDto> findBySlotId(Long slotId);

    Optional<SlotLanguageDto> findBySlotIdAndSlotLanguageId(Long slotId, Long languageId);
}
