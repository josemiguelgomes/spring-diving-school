package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    @Mock
    CourseDtoService courseDtoService;

    CourseController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CourseController(courseDtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/index"));
    }

    @Test
    public void test_listCourses() throws Exception {
        //given
        Set<CourseDto> coursesDto = new HashSet<>();

        //when
        when(courseDtoService.findAll()).thenReturn(coursesDto);

        //then
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/index"))
                .andExpect(model().attributeExists("courses"));
    }

    @Test
    public void test_showById() throws Exception {
        //given
        CourseDto courseDto = new CourseDto();
        courseDto.setId(1L);

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(Optional.of(courseDto));

        //then
        mockMvc.perform(get("/courses/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/show"))
                .andExpect(model().attributeExists("course"));
    }


    @Test
    public void test_newCourse() throws Exception {
        CourseDto courseDto = new CourseDto();

        mockMvc.perform(get("/courses/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/courseform"))
                .andExpect(model().attributeExists("course"));
    }

    @Test
    public void test_UpdateCourse() throws Exception {
        //given
        CourseDto courseDto = new CourseDto();
        courseDto.setId(2L);

        //when
        when(courseDtoService.save(any())).thenReturn(courseDto);

        //then
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("name", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses/2/show"));
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        CourseDto courseDto = new CourseDto();
        courseDto.setId(2L);

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(Optional.of(courseDto));

        //then
        mockMvc.perform(get("/courses/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/courseform"))
                .andExpect(model().attributeExists("course"));
    }

    @Test
    public void test_deleteById() throws Exception {
        mockMvc.perform(get("/courses/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses"));

        verify(courseDtoService, times(1)).deleteById(anyLong());
    }

    @Test
    public void test_listCourseSlots() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showCourseSlot() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

}
