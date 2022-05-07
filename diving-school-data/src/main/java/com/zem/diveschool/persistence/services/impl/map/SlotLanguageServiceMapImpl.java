package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Profile({"default", "map"})
@Service
public class SlotLanguageServiceMapImpl extends AbstractServiceMapsImpl<SlotLanguage, Long>
                                    implements SlotLanguageService {
    @Override
    @Transactional
    public Set<SlotLanguage> findAll() {
        return super.findAll();
    }

     @Override
    @Transactional
    public Optional<SlotLanguage> findById(Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public SlotLanguage save(SlotLanguage object) {
        if (object == null) {
            return null;
        }
        return super.save(object);
    }
    @Override
    @Transactional
    public void delete(SlotLanguage object) {
        super.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
