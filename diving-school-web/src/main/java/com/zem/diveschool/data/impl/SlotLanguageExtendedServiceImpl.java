package com.zem.diveschool.data.impl;

import com.zem.diveschool.data.SlotLanguageExtendedService;
import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SlotLanguageExtendedServiceImpl extends AbstractExtendedServiceImpl<SlotLanguage, Long, SlotLanguageService>
                                implements SlotLanguageExtendedService {

    @Override
    public Set<SlotLanguage> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<SlotLanguage> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public SlotLanguage save(SlotLanguage object) {
        return super.save(object);
    }

    @Override
    public void delete(SlotLanguage object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public <S extends SlotLanguage> List<S> saveAll(@NotNull Iterable<S> entities) {
        return super.saveAll(entities);
    }
}

