package com.zem.diveschool.services.springdatajpa;

import com.zem.diveschool.converters.ConverterDtoEntityService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.repositories.CardRepository;
import com.zem.diveschool.persistence.services.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { MyTestConfiguration.class })
@SpringBootTest
class CardServiceIT {
    public static final String NEW_COURSE = "New Course";

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ConverterDtoEntityService<CardDto, Card> converter;



    @Transactional
    @Test
    void testSaveOfCourse() throws Exception {
        //given
        Iterable<Card> cards = cardRepository.findAll();
        Card testCard = cards.iterator().next();
        CardDto testCardDto = converter.convertFromEntity(testCard);

        //when
        testCardDto.setCourse(NEW_COURSE);
        Card savedCard = cardService.save(converter.convertFromDto(testCardDto));
        CardDto savedCardDto = converter.convertFromEntity(savedCard);

        //then
        assertEquals(NEW_COURSE, savedCardDto.getCourse());
        assertEquals(testCard.getId(), savedCardDto.getId());
//      assertEquals(testCard.getxxx().size(), savedCardDto.getxxx().size());
//      assertEquals(testCard.getyyy().size(), savedCardDto.getyyy().size());
    }
}
