package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CourseConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.CourseExtendedService;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    @Mock
    private CourseConverter converter;

    @Mock
    private SlotConverter slotConverter;

    @Mock
    CourseExtendedService service;

    @Mock
    SlotExtendedService slotService;

    CourseController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CourseController(service, slotService, converter, slotConverter);
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
        Set<Course> courses = new HashSet<>();
        Set<CourseDto> coursesDto = new HashSet<>();

        //when
        when(service.findAll()).thenReturn(courses);
        when(converter.convertFromEntities(anyCollection())).thenReturn(coursesDto);

        //then
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/index"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().size(1));

        verify(service, times(1)).findAll();
        verify(converter, times(1)).convertFromEntities(anyCollection());
    }

    @Test
    public void test_showById() throws Exception {
        //given
        Optional<Course> courseOptional = Optional.of(new Course());
        CourseDto courseDto = new CourseDto();

        //when
        when(service.findById(anyLong())).thenReturn(courseOptional);
        when(converter.convertFromEntity(any(Course.class))).thenReturn(courseDto);

        //then
        mockMvc.perform(get("/courses/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/show"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(service, times(1)).findById(anyLong());
        verify(converter, times(1)).convertFromEntity(any(Course.class));
    }


    @Test
    public void test_newCourse() throws Exception {
        //given

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
        Optional<Course> courseOptional = Optional.of(new Course());
        CourseDto courseDto = new CourseDto();

        //when
        when(service.findById(anyLong())).thenReturn(courseOptional);
        when(converter.convertFromEntity(any(Course.class))).thenReturn(courseDto);

        //then
        mockMvc.perform(get("/courses/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/courseform"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(service, times(1)).findById(anyLong());
        verify(converter, times(1)).convertFromEntity(any(Course.class));
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        Course course = new Course();
        Course savedCourse = new Course();
        CourseDto savedCourseDto = new CourseDto();

        //when
        when(converter.convertFromDto(any(CourseDto.class))).thenReturn(course);
        when(service.save(any(Course.class))).thenReturn(savedCourse);
        when(converter.convertFromEntity(any(Course.class))).thenReturn(savedCourseDto);

        //then
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("name", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses/2/show"))
                .andExpect(model().size(1));

        verify(converter, times(1)).convertFromDto(any(CourseDto.class));
        verify(service, times(1)).save(any(Course.class));
        verify(converter, times(1)).convertFromEntity(any(Course.class));
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

        verify(service, times(1)).deleteById(anyLong());
    }

    /* ---- */

    @Test
    public void test_findCourses() throws Exception {
        //given
        Set<Course> courses = new HashSet<>();
        Set<CourseDto> coursesDto = new HashSet<>();

        //when
        when(service.findAll()).thenReturn(courses);
        when(converter.convertFromEntities(anyCollection())).thenReturn(coursesDto);

        //then
        mockMvc.perform(get("/courses/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/find"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().size(1));

        verify(service, times(1)).findAll();
        verify(converter, times(1)).convertFromEntities(anyCollection());
    }

    /* ---- */

    @Test
    public void test_listCourseSlots() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());
        Set<SlotDto> slotsDto = new HashSet<SlotDto>();

        //when
        when(service.findById(anyLong())).thenReturn(courseDtoOptional);
        when(service.findSlotsByCourseId(anyLong())).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/courses/1/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/slots/list"))
                .andExpect(model().attributeExists("slots"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(2));

        verify(service, times(1)).findById(anyLong());
        verify(service, times(1)).findSlotsByCourseId(anyLong());
        */
    }

    @Test
    public void test_newCourseSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());

        //when
        when(service.findById(anyLong())).thenReturn(courseDtoOptional);

        //then
        mockMvc.perform(get("/courses/1/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/locations/locationform"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

        verify(service, times(1)).findById(anyLong());
         */
    }

    @Test
    public void test_deleteCourseSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());

        //when
        when(service.findById(anyLong())).thenReturn(courseDtoOptional);

        //then
        mockMvc.perform(get("/courses/1/slots/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses/slots"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(service, times(1)).findById(anyLong());
        verify(service, times(1)).deleteByCourseIdAndSlotId(anyLong(), anyLong());
         */
    }

    @Test
    public void test_showCourseSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(service.findById(anyLong())).thenReturn(courseDtoOptional);
        when(service.findByCourseIdAndSlotId(anyLong(), anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/courses/1/slots/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/slots/show"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(2));

        verify(service, times(1)).findById(anyLong());
        verify(service, times(1)).findByCourseIdAndSlotId(anyLong(), anyLong());
         */
    }

}
