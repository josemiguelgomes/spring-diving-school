package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.SlotDto;
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
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).findAll();
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
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).findById(anyLong());
    }


    @Test
    public void test_newCourse() throws Exception {
        //given
        CourseDto courseDto = new CourseDto();

        //when

        //then
        mockMvc.perform(get("/courses/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/courseform"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));
    }

    @Test
    public void test_updateCourse() throws Exception {
        //given
        CourseDto courseDto = new CourseDto();
        courseDto.setId(2L);

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(Optional.of(courseDto));

        //then
        mockMvc.perform(get("/courses/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/courseform"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
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
                .andExpect(view().name("redirect:/courses/2/show"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).save(any());
    }

    @Test
    public void test_deleteById() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/courses/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses"))
                .andExpect(model().size(0));

        verify(courseDtoService, times(1)).deleteById(anyLong());
    }

    /* ---- */

    @Test
    public void test_findCourses() throws Exception {
        //given
        Set<CourseDto> coursesDto = new HashSet<>();

        //when
        when(courseDtoService.findAll()).thenReturn(coursesDto);

        //then
        mockMvc.perform(get("/courses/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/find"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).findAll();
    }

    /* ---- */

    @Test
    public void test_listCourseSlots() throws Exception {
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());
        Set<SlotDto> slotsDto = new HashSet<SlotDto>();

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(courseDtoOptional);
        when(courseDtoService.findSlotsByCourseId(anyLong())).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/courses/1/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/slots/list"))
                .andExpect(model().attributeExists("slots"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(2));

        verify(courseDtoService, times(1)).findById(anyLong());
        verify(courseDtoService, times(1)).findSlotsByCourseId(anyLong());
    }

    @Test
    public void test_newCourseSlot() throws Exception {
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(courseDtoOptional);

        //then
        mockMvc.perform(get("/courses/1/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/locationform"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).findById(anyLong());
    }

    @Test
    public void test_deleteCourseSlot() throws Exception {
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(courseDtoOptional);

        //then
        mockMvc.perform(get("/courses/1/slots/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses/slots"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(courseDtoService, times(1)).findById(anyLong());
        verify(courseDtoService, times(1)).deleteByCourseIdAndSlotId(anyLong(), anyLong());
    }

    @Test
    public void test_showCourseSlot() throws Exception {
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(courseDtoService.findById(anyLong())).thenReturn(courseDtoOptional);
        when(courseDtoService.findByCourseIdAndSlotId(anyLong(), anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/courses/1/slots/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/slots/show"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(2));

        verify(courseDtoService, times(1)).findById(anyLong());
        verify(courseDtoService, times(1)).findByCourseIdAndSlotId(anyLong(), anyLong());
    }

}
