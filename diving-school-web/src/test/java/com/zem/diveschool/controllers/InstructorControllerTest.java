package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.services.InstructorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class InstructorControllerTest {

    @Mock
    InstructorService instructorService;

    @Mock
    ConvertObjectToObject<Instructor, InstructorDto> convertToDto;
    @Mock
    ConvertObjectToObject<InstructorDto, Instructor> convertToEntity;


    @Mock
    Model model;

    InstructorController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new InstructorController(instructorService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/index"));
    }

    @Test
    public void requestListInstructors() throws Exception {
        //given
        Set<InstructorDto> instructorsDto = new HashSet<>();

        InstructorDto instructorDto1 = new InstructorDto();
        instructorDto1.setId(1L);
        instructorsDto.add(instructorDto1);

        InstructorDto instructorDto2 = new InstructorDto();
        instructorDto2.setId(2L);
        instructorsDto.add(instructorDto2);


        when(convertToDto.convert(instructorService.findAll())).thenReturn(instructorsDto);

        ArgumentCaptor<Set<InstructorDto>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.listInstructors(model);

        //then
        assertEquals("instructors/index", viewName);
        verify(model, times(1)).addAttribute(eq("instructors"), argumentCaptor.capture());
        Set<InstructorDto> setInController = argumentCaptor.getValue();
        assertEquals(instructorsDto.size(), setInController.size());
    }

    @Test
    public void requestShowById() throws Exception {
        //given
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(1L);

        when(convertToDto.convert(instructorService.findById(1L))).thenReturn(instructorDto);

        ArgumentCaptor<InstructorDto> argumentCaptor = ArgumentCaptor.forClass(InstructorDto.class);

        //when
        String id = Long.valueOf(1L).toString();
        String viewName = controller.showById(id, model);

        //then
        assertEquals("instructors/show", viewName);
        verify(model, times(1)).addAttribute(eq("instructor"), argumentCaptor.capture());
        InstructorDto inController = argumentCaptor.getValue();
        assertEquals(Optional.of(1L), Optional.ofNullable(inController.getId()));
    }


    @Test
    public void getNewInstructor() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);
    }

    @Test
    public void getUpdateInstructor() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);

    }

    @Test
    public void postSaveOrUpdate() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);

    }

    @Test
    public void getDeleteById() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);
    }

}
