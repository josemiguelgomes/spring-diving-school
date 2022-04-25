package com.zem.zemdivingschool.persistence.services.impl.map;

import com.zem.zemdivingschool.persistence.model.SlotLanguage;
import com.zem.zemdivingschool.persistence.services.SlotLanguageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class SlotLanguageMapService extends AbstractMapService<SlotLanguage, Long>
                                    implements SlotLanguageService {
    @Override
    @Transactional
    public Set<SlotLanguage> findAll() {
        return super.findAll();
    }

     @Override
    @Transactional
    public SlotLanguage findById(Long id) {
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
