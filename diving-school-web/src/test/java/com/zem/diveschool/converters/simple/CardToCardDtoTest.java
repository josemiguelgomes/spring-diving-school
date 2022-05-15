package com.zem.diveschool.converters.simple;


import com.zem.diveschool.converters.ConverterDtoEntityService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Country;
import com.zem.diveschool.persistence.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class CardToCardDtoTest {
    public static final Long ID1 = 1L;
    public static final String ID1_S = "1";
    public static final String COURSE1 = "COURSE1";
    public static final String STUDENT1 = "STUDENT1";
    public static       Date START_DATE1 = new Date();
    public static       Date END_DATE1 = new Date();;
    public static final String INSTRUCTOR1 = "INSTRUCTOR1";

    public static final Long ID2 = 2L;
    public static final String ID2_S = "2";
    public static final String COURSE2 = "COURSE2";
    public static final String STUDENT2 = "STUDENT2";
    public static       Date START_DATE2 = new Date();
    public static       Date END_DATE2 = new Date();
    public static final String INSTRUCTOR2 = "INSTRUCTOR2";

    @Autowired
    ConverterDtoEntityService<CardDto, Card> converter;
    @Autowired
    ConverterDtoEntityService<StudentDto, Student> convertStudent;


    @Before
    public void setUp() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        START_DATE1 = sdf.parse("1980-01-01");
        END_DATE1 = sdf.parse("1980-01-05");

        START_DATE2 = sdf.parse("2010-05-01");
        END_DATE2 = sdf.parse("2010-05-07");
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convertFromEntity((Card) null));
    }

    @Test
    public void testNullParameterSet() throws Exception {
        assertNull(converter.convertFromEntities((Set<Card>) null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convertFromEntity(new Card()));
    }

    @Test
    public void test_GIVEN_entity_WHEN_convertToDto_THEN_compare() throws Exception {
        //given
        Card entity = Card.builder()
                .id(ID1)
                .course(COURSE1)
                .studentName(STUDENT1)
                .startDate(START_DATE1)
                .endDate(END_DATE1)
                .country(Country.PORTUGAL)
                .instructorName(INSTRUCTOR1)
                .student(null)
                .build();

        //when
        CardDto dto = converter.convertFromEntity(entity);

        //then
        assertNotNull(dto);
        assertEquals(ID1, dto.getId());
        assertEquals(COURSE1, dto.getCourse());
        assertEquals(STUDENT1, dto.getStudentName());
        assertEquals(START_DATE1.toString(), dto.getStartDate().toString()); // TODO refactor all dates
        assertEquals(END_DATE1.toString(), dto.getEndDate().toString()); // TODO
        assertEquals(Country.PORTUGAL, dto.getCountry());
        assertEquals(INSTRUCTOR1, dto.getInstructorName());
        assertNull(dto.getStudent());
    }

    @Test
    public void test_GIVEN_set_entity_WHEN_convertToDto_THEN_compare() throws Exception {
        //given
        Set<Card> entities = new HashSet<>();

        Card entity1 = Card.builder()
                .id(ID1)
                .course(COURSE1)
                .studentName(STUDENT1)
                .startDate(START_DATE1)
                .endDate(END_DATE1)
                .country(Country.PORTUGAL)
                .instructorName(INSTRUCTOR1)
                .student(null)
                .build();
        entities.add(entity1);

        Card entity2 = Card.builder()
                .id(ID2)
                .course(COURSE2)
                .studentName(STUDENT2)
                .startDate(START_DATE2)
                .endDate(END_DATE2)
                .country(Country.UKRAINE)
                .instructorName(INSTRUCTOR2)
                .student(null)
                .build();
        entities.add(entity2);


        //when
        Set <CardDto> dtos = converter.convertFromEntities(entities);

        //then
        assertNotNull(dtos);
        assertEquals(entities.size(), dtos.size());
        for(CardDto dto: dtos) {
            switch(dto.getId().toString()) {
                case ID1_S:
                    assertEquals(ID1, dto.getId());
                    assertEquals(COURSE1, dto.getCourse());
                    assertEquals(STUDENT1, dto.getStudentName());
                    assertEquals(START_DATE1.toString(), dto.getStartDate().toString()); // TODO refactor all dates
                    assertEquals(END_DATE1.toString(), dto.getEndDate().toString()); // TODO
                    assertEquals(Country.PORTUGAL, dto.getCountry());
                    assertEquals(INSTRUCTOR1, dto.getInstructorName());
                    assertNull(dto.getStudent());
                    break;
                case ID2_S:
                    assertEquals(ID2, dto.getId());
                    assertEquals(COURSE2, dto.getCourse());
                    assertEquals(STUDENT2, dto.getStudentName());
                    assertEquals(START_DATE2.toString(), dto.getStartDate().toString()); // TODO refactor all dates
                    assertEquals(END_DATE2.toString(), dto.getEndDate().toString()); // TODO
                    assertEquals(Country.UKRAINE, dto.getCountry());
                    assertEquals(INSTRUCTOR2, dto.getInstructorName());
                    assertNull(dto.getStudent());
                    break;
                default:
                    assertEquals("ID between 1,2", "ID different than 1 or 2");
            }
        }
    }
}
