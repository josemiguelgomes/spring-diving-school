package com.zem.diveschool.persistence.services.impl.map;

import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.services.CardService;
import com.zem.diveschool.persistence.services.StudentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class CardMapService extends AbstractMapService<Card, Long>
                            implements CardService {
    private final StudentService studentService;

    public CardMapService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public Set<Card> findAll() {
        return super.findAll();
    }
    @Override
    @Transactional
    public Card findById(Long id) {
        return super.findById(id);
    }
    @Override
    @Transactional
    public Card save(Card object) {
        if (object == null) {
            return null;
        }

        if (object.getStudent() == null) {
           throw new RuntimeException("Student is required on saving a Card");
        }

        if (object.getStudent().getId() == null) {
            object.setStudent(studentService.save(object.getStudent()));
        }

        return super.save(object);
    }
    @Override
    @Transactional
    public void delete(Card object) {
        super.delete(object);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
