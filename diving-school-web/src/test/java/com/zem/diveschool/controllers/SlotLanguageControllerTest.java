package com.zem.diveschool.controllers;

import com.zem.diveschool.data.SlotLanguageDtoService;
import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.Language;
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

public class SlotLanguageControllerTest {

    @Mock
    SlotLanguageDtoService slotLanguageDtoService;

    SlotLanguageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new SlotLanguageController(slotLanguageDtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/slotLanguages"))
                .andExpect(status().isOk())
                .andExpect(view().name("slotLanguages/index"));
    }

    @Test
    public void testAllSlotLanguages() throws Exception {
        //given
        Set<SlotLanguageDto> slotLanguagesDto = new HashSet<>();

        //when
        when(slotLanguageDtoService.findAll()).thenReturn(slotLanguagesDto);

        //then
        mockMvc.perform(get("/slotLanguages"))
                .andExpect(status().isOk())
                .andExpect(view().name("slotLanguages/index"))
                .andExpect(model().attributeExists("slotLanguages"));
    }

    @Test
    public void testGetSlotLanguage() throws Exception {
        //given
        SlotLanguageDto slotLanguageDto = new SlotLanguageDto();
        slotLanguageDto.setId(1L);

        //when
        when(slotLanguageDtoService.findById(anyLong())).thenReturn(slotLanguageDto);

        //then
        mockMvc.perform(get("/slotLanguages/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("slotLanguages/show"))
                .andExpect(model().attributeExists("slotLanguage"));
    }


    @Test
    public void testGetNewSlotLanguageForm() throws Exception {
        SlotLanguageDto instructorDto = new SlotLanguageDto();

        mockMvc.perform(get("/slotLanguages/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slotLanguages/slotLanguageform"))
                .andExpect(model().attributeExists("slotLanguage"));
    }

    @Test
    public void testPostNewSlotLanguageForm() throws Exception {
        //given
        SlotLanguageDto slotLanguageDto = new SlotLanguageDto();
        slotLanguageDto.setId(2L);

        //when
        when(slotLanguageDtoService.save(any())).thenReturn(slotLanguageDto);

        //then
        mockMvc.perform(post("/slotLanguages")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("language", String.valueOf(Language.PORTUGUESE))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slotLanguages/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        //given
        SlotLanguageDto slotLanguageDto = new SlotLanguageDto();
        slotLanguageDto.setId(2L);

        //when
        when(slotLanguageDtoService.findById(anyLong())).thenReturn(slotLanguageDto);

        //then
        mockMvc.perform(get("/slotLanguages/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("slotLanguages/slotLanguageform"))
                .andExpect(model().attributeExists("slotLanguage"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/slotLanguages/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/slotLanguages"));

        verify(slotLanguageDtoService, times(1)).deleteById(anyLong());
    }

}
