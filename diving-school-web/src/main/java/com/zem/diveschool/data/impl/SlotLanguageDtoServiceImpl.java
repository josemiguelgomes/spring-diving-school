package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.SlotLanguageDtoService;
import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SlotLanguageDtoServiceImpl extends AbstractDtoServiceImpl<SlotLanguageDto, Long, SlotLanguage, SlotLanguageService>
                                implements SlotLanguageDtoService {

    @Override
    public Set<SlotLanguageDto> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<SlotLanguageDto> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public SlotLanguageDto save(SlotLanguageDto object) {
        return super.save(object);
    }

    @Override
    public void delete(SlotLanguageDto object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends SlotLanguageDto> List<S> saveAll(@NotNull Iterable<S> dtos) {
        return super.saveAll(dtos);
    }
}

