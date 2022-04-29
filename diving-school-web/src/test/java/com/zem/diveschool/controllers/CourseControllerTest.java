package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CourseControllerTest {

    @Mock
    CourseService courseService;

    @Mock
    ConvertObjectToObject<Course, CourseDto> convertToDto;
    @Mock
    ConvertObjectToObject<CourseDto, Course> convertToEntity;


    @Mock
    Model model;

    CourseController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CourseController(courseService, convertToDto, convertToEntity);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/index"));
    }

    @Test
    public void getListCourses() throws Exception {
        //given
        Set<CourseDto> coursesDto = new HashSet<>();
        coursesDto.add(new CourseDto());

        CourseDto courseDto = new CourseDto();
        courseDto.setId(1L);

        coursesDto.add(courseDto);

        when(convertToDto.convert(courseService.findAll())).thenReturn(coursesDto);

        ArgumentCaptor<Set<CourseDto>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.listCourses(model);

        //then
        assertEquals("courses/index", viewName);
        verify(model, times(1)).addAttribute(eq("courses"), argumentCaptor.capture());
        Set<CourseDto> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}
