package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.*;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.*;
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

public class SlotControllerTest {

    @Mock
    SlotExtendedService service;

    @Mock
    SlotConverter converter;

    @Mock
    CourseConverter courseConverter;

    @Mock
    InstructorConverter instructorConverter;

    @Mock
    SlotLanguageConverter slotLanguageConverter;

    @Mock
    StudentConverter studentConverter;

    SlotController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new SlotController(service, converter, courseConverter, instructorConverter,
                                        slotLanguageConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/index"));
    }

    @Test
    public void test_listSlots() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Set<SlotDto> slotsDto = new HashSet<>();

        //when
        when(slotExtendedService.findAll()).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/index"))
                .andExpect(model().attributeExists("slots"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findAll();

         */
    }

    @Test
    public void test_showById() throws Exception {
        assertEquals(1,0);
        /*
        //given
        SlotDto slotDto = new SlotDto();
        slotDto.setId(1L);

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(Optional.of(slotDto));

        //then
        mockMvc.perform(get("/slots/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/show"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_newSlot() throws Exception {
        /*
        //given
        SlotDto instructorDto = new SlotDto();

        //when

        //then
        mockMvc.perform(get("/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotform"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

         */
    }

    @Test
    public void test_updateSlot() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotform"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(1));

         */
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        assertEquals(1,0);
        /*
        //given
        SlotDto slotDto = new SlotDto();
        slotDto.setId(2L);

        //when
        when(slotExtendedService.save(any())).thenReturn(slotDto);

        //then
        mockMvc.perform(post("/slots")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("title", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots/2/show"))
                .andExpect(model().size(1)); // ????

        verify(slotExtendedService, times(1)).save(any());

         */
    }

    @Test
    public void test_deleteById() throws Exception {
        assertEquals(1,0);
        /*
        //given

        //when

        //then
        mockMvc.perform(get("/slots/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots"))
                .andExpect(model().size(0));

        verify(slotExtendedService, times(1)).deleteById(anyLong());

         */
    }

    /* ---- */

    @Test
    public void test_findSlots() throws Exception {
        assertEquals(1,0);
        /*
        //given

        //when

        //then
        mockMvc.perform(get("/slots/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/find"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findAll();

         */
    }

    /* ---- */

    @Test
    public void test_listSlotCourses() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Set<CourseDto> coursesDto = new HashSet<>();

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findCoursesBySlotId(anyLong())).thenReturn(coursesDto);

        //then
        mockMvc.perform(get("/slots/1/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/courses/list"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findCoursesBySlotId(anyLong());

         */
    }

    @Test
    public void test_newSlotCourse() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/courses/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/courses/courseform"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteSlotCourse() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/courses/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots/courses"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).deleteBySlotIdAndCourseId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showSlotCourse() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Optional<CourseDto> courseDtoOptional = Optional.of(new CourseDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findBySlotIdAndCourseId(anyLong(), anyLong())).thenReturn(courseDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/courses/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/courses/show"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findBySlotIdAndCourseId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_listSlotInstructors() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Set<InstructorDto> instructorsDto = new HashSet<>();

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findInstructorsBySlotId(anyLong())).thenReturn(instructorsDto);

        //then
        mockMvc.perform(get("/slots/1/instructors"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/instructors/list"))
                .andExpect(model().attributeExists("instructors"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findInstructorsBySlotId(anyLong());

         */
    }

    @Test
    public void test_newSlotInstructor() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/instructors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/instructors/instructorform"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteSlotInstructor() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/instructors/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots/instructors"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).deleteBySlotIdAndInstructorId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showSlotInstructor() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Optional<InstructorDto> instructorDtoOptional = Optional.of(new InstructorDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findBySlotIdAndInstructorId(anyLong(), anyLong())).thenReturn(instructorDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/instructors/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/instructors/show"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findBySlotIdAndInstructorId(anyLong(), anyLong());

         */
    }

    @Test
    public void list_listSlotSlotLanguages() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Set<SlotLanguageDto> slotLanguagesDto = new HashSet<>();

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findLanguagesBySlotId(anyLong())).thenReturn(slotLanguagesDto);

        //then
        mockMvc.perform(get("/slots/1/slotlanguages"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotLanguages/list"))
                .andExpect(model().attributeExists("slotLanguages"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findLanguagesBySlotId(anyLong());

         */
    }

    @Test
    public void test_newSlotSlotLanguage() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/slotLanguages/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotLanguages/slotLanguageform"))
                .andExpect(model().attributeExists("slotLanguage"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteSlotSlotLanguage() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/slotlanguages/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots/slotLanguages"))
                .andExpect(model().attributeExists("slotLanguage"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).deleteBySlotIdAndSlotLanguageId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showSlotSlotLanguage() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Optional<SlotLanguageDto> slotLanguageDtoOptional = Optional.of(new SlotLanguageDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findBySlotIdAndSlotLanguageId(anyLong(), anyLong())).thenReturn(slotLanguageDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/slotlanguages/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotLanguages/show"))
                .andExpect(model().attributeExists("slotLanguage"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findBySlotIdAndSlotLanguageId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_listSlotStudents() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Set<StudentDto> studentsDto = new HashSet<>();

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findStudentsBySlotId(anyLong())).thenReturn(studentsDto);

        //then
        mockMvc.perform(get("/slots/1/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/students/list"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findStudentsBySlotId(anyLong());

         */
    }

    @Test
    public void test_newSlotStudent() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/student/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/student/studentform"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());

         */
    }

    @Test
    public void test_deleteSlotStudent() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/student/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots/students"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().size(1));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).deleteBySlotIdAndStudentId(anyLong(), anyLong());

         */
    }

    @Test
    public void test_showSlotStudent() throws Exception {
        assertEquals(1,0);
        /*
        //given
        Optional<SlotDto> slotDtoOptional = Optional.of(new SlotDto());
        Optional<StudentDto> studentDtoOptional = Optional.of(new StudentDto());

        //when
        when(slotExtendedService.findById(anyLong())).thenReturn(slotDtoOptional);
        when(slotExtendedService.findBySlotIdAndStudentId(anyLong(), anyLong())).thenReturn(studentDtoOptional);

        //then
        mockMvc.perform(get("/slots/1/students/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/students/show"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().size(2));

        verify(slotExtendedService, times(1)).findById(anyLong());
        verify(slotExtendedService, times(1)).findBySlotIdAndStudentId(anyLong(), anyLong());

         */
    }

}

