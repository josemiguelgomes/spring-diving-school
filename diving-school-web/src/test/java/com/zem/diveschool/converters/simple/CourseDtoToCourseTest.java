package com.zem.diveschool.converters.simple;


import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.converters.impl.simple.CourseDtoToCourseImpl;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Level;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CourseDtoToCourseTest {
    public static final Long ID1 = 1L;
    public static final String ID1_S = "1";
    public static final String NAME1 = "NAME1";
    public static final Level LEVEL1 = Level.DIFFICULT;

    public static final Long ID2 = 2L;
    public static final String ID2_S = "2";
    public static final String NAME2 = "NAME2";
    public static final Level LEVEL2 = Level.MEDIUM;


    ConvertObjectToObject<CourseDto, Course> converter;

    @Before
    public void setUp() throws Exception {
//        converter = new CourseDtoToCourseImpl(courseConverterToEntity);
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert((CourseDto) null));
    }

    @Test
    public void testNullParameterSet() throws Exception {
        assertNull(converter.convert((Set<CourseDto>) null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CourseDto()));
    }

    @Test
    public void test_GIVEN_dto_WHEN_convertToEntity_THEN_compare() throws Exception {
        //given
        CourseDto dto = CourseDto.builder()
                .id(ID1)
                .name(NAME1)
                .level(LEVEL1)
                .slots(null)
                .build();

        //when
        Course entity = converter.convert(dto);

        //then
        assertNotNull(entity);
        assertEquals(ID1, entity.getId());
        assertEquals(NAME1, entity.getName());
        assertEquals(LEVEL1, entity.getLevel());
    }

    @Test
    public void test_GIVEN_set_dto_WHEN_convertToEntity_THEN_compare() throws Exception {
        //given
        Set<CourseDto> dtos = new HashSet<>();

        CourseDto dto1 = CourseDto.builder()
                .id(ID1)
                .name(NAME1)
                .level(LEVEL1)
                .build();
        dtos.add(dto1);

        CourseDto dto2 = CourseDto.builder()
                .id(ID2)
                .name(NAME2)
                .level(LEVEL2)
                .build();
        dtos.add(dto2);


        //when
        Set <Course> entities = converter.convert(dtos);

        //then
        assertNotNull(entities);
        assertEquals(dtos.size(), entities.size());
        for(Course entity: entities) {
           switch(entity.getId().toString()) {
               case ID1_S:
                   assertEquals(ID1, entity.getId());
                   assertEquals(NAME1, entity.getName());
                   assertEquals(LEVEL1, entity.getLevel());
                   break;
               case ID2_S:
                   assertEquals(ID2, entity.getId());
                   assertEquals(NAME2, entity.getName());
                   assertEquals(LEVEL2, entity.getLevel());
                   break;
               default:
                   assertEquals("ID between 1,2", "ID different than 1 or 2");
           }
        }
    }
}
