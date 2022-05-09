package com.zem.diveschool.converters.simple;


import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.converters.impl.simple.CourseToCourseDtoImpl;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Level;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseToCourseDtoTest {
    public static final Long ID1 = 1L;
    public static final String ID1_S = "1";
    public static final String NAME1 = "NAME1";
    public static final Level LEVEL1 = Level.DIFFICULT;

    public static final Long ID2 = 2L;
    public static final String ID2_S = "2";
    public static final String NAME2 = "NAME2";
    public static final Level LEVEL2 = Level.MEDIUM;


    ConvertObjectToObject<Course, CourseDto> converter;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert((Course) null));
    }

    @Test
    public void testNullParameterSet() throws Exception {
        assertNull(converter.convert((Set<Course>) null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Course()));
    }

    @Test
    public void test_GIVEN_Entity_WHEN_convertToDto_THEN_compare() throws Exception {
        //given
        Course entity = Course.builder()
                .id(ID1)
                .name(NAME1)
                .level(LEVEL1)
                .slots(null)
                .build();

        //when
        CourseDto dto = converter.convert(entity);

        //then
        assertNotNull(entity);
        assertEquals(ID1, dto.getId());
        assertEquals(NAME1, dto.getName());
        assertEquals(LEVEL1, dto.getLevel());
    }

    @Test
    public void test_GIVEN_set_entity_WHEN_convertToDto_THEN_compare() throws Exception {
        //given
        Set<Course> entities = new HashSet<>();

        Course entity1 = Course.builder()
                .id(ID1)
                .name(NAME1)
                .level(LEVEL1)
                .build();
        entities.add(entity1);

        Course entity2 = Course.builder()
                .id(ID2)
                .name(NAME2)
                .level(LEVEL2)
                .build();
        entities.add(entity2);


        //when
        Set <CourseDto> dtos = converter.convert(entities);

        //then
        assertNotNull(dtos);
        assertEquals(entities.size(), dtos.size());
        for(CourseDto dto: dtos) {
           switch(dto.getId().toString()) {
               case ID1_S:
                   assertEquals(ID1, dto.getId());
                   assertEquals(NAME1, dto.getName());
                   assertEquals(LEVEL1, dto.getLevel());
                   break;
               case ID2_S:
                   assertEquals(ID2, dto.getId());
                   assertEquals(NAME2, dto.getName());
                   assertEquals(LEVEL2, dto.getLevel());
                   break;
               default:
                   assertEquals("ID between 1,2", "ID different than 1 or 2");
           }
        }
    }
}
