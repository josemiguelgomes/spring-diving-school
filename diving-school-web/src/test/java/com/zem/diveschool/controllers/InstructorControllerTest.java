package com.zem.diveschool.controllers;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.InstructorDto;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InstructorControllerTest {

    @Mock
    InstructorDtoService instructorDtoService;

    InstructorController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new InstructorController(instructorDtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/index"));
    }

    @Test
    public void testAllInstructors() throws Exception {
        //given
        Set<InstructorDto> instructorsDto = new HashSet<>();

        //when
        when(instructorDtoService.findAll()).thenReturn(instructorsDto);

        //then
        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/index"))
                .andExpect(model().attributeExists("instructors"));
    }

    @Test
    public void testGetInstructor() throws Exception {
        //given
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(1L);

        //when
        when(instructorDtoService.findById(anyLong())).thenReturn(instructorDto);

        //then
        mockMvc.perform(get("/instructors/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/show"))
                .andExpect(model().attributeExists("instructor"));
    }


    @Test
    public void testGetNewInstructorForm() throws Exception {
        InstructorDto instructorDto = new InstructorDto();

        mockMvc.perform(get("/instructors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/instructorform"))
                .andExpect(model().attributeExists("instructor"));
    }

    @Test
    public void testPostNewInstructorForm() throws Exception {
        //given
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(2L);

        //when
        when(instructorDtoService.save(any())).thenReturn(instructorDto);

        //then
        mockMvc.perform(post("/instructors")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("firstName", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        //given
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(2L);

        //when
        when(instructorDtoService.findById(anyLong())).thenReturn(instructorDto);

        //then
        mockMvc.perform(get("/instructors/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructors/instructorform"))
                .andExpect(model().attributeExists("instructor"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/instructors/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors"));

        verify(instructorDtoService, times(1)).deleteById(anyLong());
    }

}
