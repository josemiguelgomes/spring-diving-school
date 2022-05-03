package com.zem.diveschool.converters.simple;


import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.converters.impl.simple.CardDtoToCardImpl;
import com.zem.diveschool.converters.impl.simple.StudentDtoToStudentImpl;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CardDtoToCardTest {
    public static final Long ID1 = 1L;
    public static final String ID1_S = "1";
    public static final String COURSE1 = "COURSE1";
    public static final String STUDENT1 = "STUDENT1";
    public static final String START_DATE1 = "2010-01-01";
    public static final String END_DATE1 = "2010-01-05";
    public static final String INSTRUCTOR1 = "INSTRUCTOR1";

    public static final Long ID2 = 2L;
    public static final String ID2_S = "2";
    public static final String COURSE2 = "COURSE2";
    public static final String STUDENT2 = "STUDENT2";
    public static final String START_DATE2 = "2012-01-01";
    public static final String END_DATE2 = "2012-01-05";
    public static final String INSTRUCTOR2 = "INSTRUCTOR2";


    ConvertObjectToObject<CardDto, Card> converter;
    ConvertObjectToObject<StudentDto, Student> convertStudent;

    @Before
    public void setUp() throws Exception {
        convertStudent = new StudentDtoToStudentImpl();
        converter = new CardDtoToCardImpl(convertStudent);
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert((CardDto) null));
    }

    @Test
    public void testNullParameterSet() throws Exception {
        assertNull(converter.convert((Set<CardDto>) null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CardDto()));
    }

    @Test
    public void test_GIVEN_dto_WHEN_convertToEntity_nullStudent_THEN_compare() throws Exception {
        //given
        CardDto dto = CardDto.builder()
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
        Card entity = converter.convert(dto);

        //then
        assertNotNull(entity);
        assertEquals(ID1, entity.getId());
        assertEquals(COURSE1, entity.getCourse());
        assertEquals(STUDENT1, entity.getStudentName());
        assertEquals(START_DATE1, entity.getStartDate().toString()); // TODO refactor all dates
        assertEquals(END_DATE1, entity.getEndDate().toString()); // TODO
        assertEquals(Country.PORTUGAL, entity.getCountry());
        assertEquals(INSTRUCTOR1, entity.getInstructorName());
        assertNull(entity.getStudent());
    }

    @Test
    public void test_GIVEN_dto_WHEN_convertToEntity_THEN_compare() throws Exception {
        //given
        CardDto dto = CardDto.builder()
                .id(ID1)
                .course(COURSE1)
                .studentName(STUDENT1)
                .startDate(START_DATE1)
                .endDate(END_DATE1)
                .country(Country.PORTUGAL)
                .instructorName(INSTRUCTOR1)
                .student(new StudentDto())
                .build();

        //when
        Card entity = converter.convert(dto);

        //then
        assertNotNull(entity);
        assertEquals(ID1, entity.getId());
        assertEquals(COURSE1, entity.getCourse());
        assertEquals(STUDENT1, entity.getStudentName());
        assertEquals(START_DATE1, entity.getStartDate().toString()); // TODO refactor all dates
        assertEquals(END_DATE1, entity.getEndDate().toString()); // TODO
        assertEquals(Country.PORTUGAL, entity.getCountry());
        assertEquals(INSTRUCTOR1, entity.getInstructorName());
        assertNotNull(entity.getStudent());
    }

    @Test
    public void test_GIVEN_set_dto_WHEN_convertToEntity_nullStudent_THEN_compare() throws Exception {
        //given
        Set<CardDto> dtos = new HashSet<>();

        CardDto dto1 = CardDto.builder()
                .id(ID1)
                .course(COURSE1)
                .studentName(STUDENT1)
                .startDate(START_DATE1)
                .endDate(END_DATE1)
                .country(Country.PORTUGAL)
                .instructorName(INSTRUCTOR1)
                .student(null)
                .build();
        dtos.add(dto1);

        CardDto dto2 = CardDto.builder()
                .id(ID2)
                .course(COURSE2)
                .studentName(STUDENT2)
                .startDate(START_DATE2)
                .endDate(END_DATE2)
                .country(Country.UKRAINE)
                .instructorName(INSTRUCTOR2)
                .student(null)
                .build();
        dtos.add(dto2);


        //when
        Set <Card> entities = converter.convert(dtos);

        //then
        assertNotNull(entities);
        assertEquals(dtos.size(), entities.size());
        for(Card entity: entities) {
           switch(entity.getId().toString()) {
               case ID1_S:
                   assertEquals(ID1, entity.getId());
                   assertEquals(COURSE1, entity.getCourse());
                   assertEquals(STUDENT1, entity.getStudentName());
                   assertEquals(START_DATE1, entity.getStartDate().toString()); // TODO refactor all dates
                   assertEquals(END_DATE1, entity.getEndDate().toString()); // TODO
                   assertEquals(Country.PORTUGAL, entity.getCountry());
                   assertEquals(INSTRUCTOR1, entity.getInstructorName());
                   assertNull(entity.getStudent());
                   break;
               case ID2_S:
                   assertEquals(ID2, entity.getId());
                   assertEquals(COURSE2, entity.getCourse());
                   assertEquals(STUDENT2, entity.getStudentName());
                   assertEquals(START_DATE2, entity.getStartDate().toString()); // TODO refactor all dates
                   assertEquals(END_DATE2, entity.getEndDate().toString()); // TODO
                   assertEquals(Country.UKRAINE, entity.getCountry());
                   assertEquals(INSTRUCTOR2, entity.getInstructorName());
                   assertNull(entity.getStudent());
                   break;
               default:
                   assertEquals("ID between 1,2", "ID different than 1 or 2");
           }
        }
    }

    @Test
    public void test_GIVEN_set_dto_WHEN_convertToEntity_THEN_compare() throws Exception {
        //given
        Set<CardDto> dtos = new HashSet<>();

        CardDto dto1 = CardDto.builder()
                .id(ID1)
                .course(COURSE1)
                .studentName(STUDENT1)
                .startDate(START_DATE1)
                .endDate(END_DATE1)
                .country(Country.PORTUGAL)
                .instructorName(INSTRUCTOR1)
                .student(new StudentDto())
                .build();
        dtos.add(dto1);

        CardDto dto2 = CardDto.builder()
                .id(ID2)
                .course(COURSE2)
                .studentName(STUDENT2)
                .startDate(START_DATE2)
                .endDate(END_DATE2)
                .country(Country.UKRAINE)
                .instructorName(INSTRUCTOR2)
                .student(new StudentDto())
                .build();
        dtos.add(dto2);


        //when
        Set <Card> entities = converter.convert(dtos);

        //then
        assertNotNull(entities);
        assertEquals(dtos.size(), entities.size());
        for(Card entity: entities) {
            switch(entity.getId().toString()) {
                case ID1_S:
                    assertEquals(ID1, entity.getId());
                    assertEquals(COURSE1, entity.getCourse());
                    assertEquals(STUDENT1, entity.getStudentName());
                    assertEquals(START_DATE1, entity.getStartDate().toString()); // TODO refactor all dates
                    assertEquals(END_DATE1, entity.getEndDate().toString()); // TODO
                    assertEquals(Country.PORTUGAL, entity.getCountry());
                    assertEquals(INSTRUCTOR1, entity.getInstructorName());
                    assertNotNull(entity.getStudent());
                    break;
                case ID2_S:
                    assertEquals(ID2, entity.getId());
                    assertEquals(COURSE2, entity.getCourse());
                    assertEquals(STUDENT2, entity.getStudentName());
                    assertEquals(START_DATE2, entity.getStartDate().toString()); // TODO refactor all dates
                    assertEquals(END_DATE2, entity.getEndDate().toString()); // TODO
                    assertEquals(Country.UKRAINE, entity.getCountry());
                    assertEquals(INSTRUCTOR2, entity.getInstructorName());
                    assertNotNull(entity.getStudent());
                    break;
                default:
                    assertEquals("ID between 1,2", "ID different than 1 or 2");
            }
        }
    }


}
