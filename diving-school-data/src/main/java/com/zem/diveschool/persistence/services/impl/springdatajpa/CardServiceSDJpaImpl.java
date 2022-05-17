package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.repositories.CardRepository;
import com.zem.diveschool.persistence.repositories.StudentRepository;
import com.zem.diveschool.persistence.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Profile({"default", "springdatajpa"})
public class CardServiceSDJpaImpl extends AbstractServiceSDJpaImpl<Card, Long, CardRepository>
                              implements CardService {

    StudentRepository studentRepository;

    protected CardServiceSDJpaImpl(CardRepository cardRepository,
                                   StudentRepository studentRepository) {
        super(cardRepository);
        this.studentRepository = studentRepository;
    }

    @Override
    public Set<Card> findByStudentID(Long id) {
        return super.findAll()
                .stream()
                .filter(p -> p.getStudent().getId().equals(id))
                .collect(Collectors.toSet());
    }

    /* TODO re-evaluate all of this !!!!
    @Override
    @Transactional
    public Card save(Card card) {
        Optional<Student> studentOptional = studentRepository.findById(card.getStudent().getId());
        if (studentOptional.isEmpty()) {
            // TODO throw error if not found
            log.error("Student not found for id " + card.getStudent().getId());
            return card;
        }

        Student student = studentOptional.get();


        Optional<Card> cardOptional = student.getCards()
                .stream()
                .filter(c -> c.getId().equals(card.getId()))
                .findFirst();

       if(cardOptional.isPresent()) {
           Card cardFound = cardOptional.get();
//         cardFound.setxxx(card.getxxx());

       } else {
           // add new Card
           student.add(card);
       }

       Student savedStudent = studentRepository.save(student);

       return savedStudent.getCards()
                .stream()
                .filter(studentCards -> studentCards.getId().equals((card.getId())))
                .findFirst()
                .orElse(null);
    }
*/
}
