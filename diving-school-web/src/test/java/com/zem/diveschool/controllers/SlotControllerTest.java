package com.zem.diveschool.controllers;

import com.zem.diveschool.data.SlotDtoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SlotControllerTest {

    @Mock
    SlotDtoService slotDtoService;

    SlotController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new SlotController(slotDtoService);
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
    public void test_listLocations() throws Exception {
        //given
        Set<SlotDto> slotsDto = new HashSet<>();

        //when
        when(slotDtoService.findAll()).thenReturn(slotsDto);

        //then
        mockMvc.perform(get("/slots"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/index"))
                .andExpect(model().attributeExists("slots"));
    }

    @Test
    public void test_showById() throws Exception {
        //given
        SlotDto slotDto = new SlotDto();
        slotDto.setId(1L);

        //when
        when(slotDtoService.findById(anyLong())).thenReturn(Optional.of(slotDto));

        //then
        mockMvc.perform(get("/slots/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/show"))
                .andExpect(model().attributeExists("slot"));
    }


    @Test
    public void test_newLocation() throws Exception {
        SlotDto instructorDto = new SlotDto();

        mockMvc.perform(get("/slots/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotform"))
                .andExpect(model().attributeExists("slot"));
    }

    @Test
    public void test_updateLocation() throws Exception {
        //given
        SlotDto slotDto = new SlotDto();
        slotDto.setId(2L);

        //when
        when(slotDtoService.save(any())).thenReturn(slotDto);

        //then
        mockMvc.perform(post("/slots")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("title", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots/2/show"));
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        SlotDto slotDto = new SlotDto();
        slotDto.setId(2L);

        //when
        when(slotDtoService.findById(anyLong())).thenReturn(Optional.of(slotDto));

        //then
        mockMvc.perform(get("/slots/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("slots/slotform"))
                .andExpect(model().attributeExists("slot"));
    }

    @Test
    public void test_deleteById() throws Exception {
        mockMvc.perform(get("/slots/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slots"));

        verify(slotDtoService, times(1)).deleteById(anyLong());
    }

    @Test
    public void test_listSlotCourses() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showSlotCourse() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_listSlotInstructors() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showSlotInstructor() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void list_listSlotSlotLanguages() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showSlotSlotLanguage() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_listSlotStudents() throws Exception {
        // TODO
        assertEquals(1, 0);
    }

    @Test
    public void test_showSlotStudent() throws Exception {
        // TODO
        assertEquals(1, 0);
    }
}
