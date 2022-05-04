package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.StudentDto;
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

public class CardControllerTest {

    @Mock
    CardDtoService cardDtoService;

    @Mock
    StudentDtoService studentDtoService;

    CardController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CardController(cardDtoService, studentDtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/index"));
    }

    @Test
    public void testAllCards() throws Exception {
        //given
        Set<CardDto> cardsDto = new HashSet<>();

        //when
        when(cardDtoService.findAll()).thenReturn(cardsDto);

        //then
        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/index"))
                .andExpect(model().attributeExists("cards"));
    }

    @Test
    public void testListCardsOfStudent() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        when(studentDtoService.findById(anyLong())).thenReturn(studentDto);

        //when
        mockMvc.perform(get("/students/1/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/cards/list"))
                .andExpect(model().attributeExists("student"));

        //then
        verify(studentDtoService, times(1)).findById(anyLong());
    }


    @Test
    public void testGetCard() throws Exception {
        //given
        CardDto cardDto = new CardDto();
        cardDto.setId(1L);

        //when
        when(cardDtoService.findById(anyLong())).thenReturn(cardDto);

        //then
        mockMvc.perform(get("/cards/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/show"))
                .andExpect(model().attributeExists("card"));
    }


    @Test
    public void testGetNewCardForm() throws Exception {
        CardDto instructorDto = new CardDto();

        mockMvc.perform(get("/cards/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/cardform"))
                .andExpect(model().attributeExists("card"));
    }

    @Test
    public void testPostNewCardForm() throws Exception {
        //given
        CardDto cardDto = new CardDto();
        cardDto.setId(2L);

        //when
        when(cardDtoService.save(any())).thenReturn(cardDto);

        //then
        mockMvc.perform(post("/cards")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("course", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cards/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        //given
        CardDto cardDto = new CardDto();
        cardDto.setId(2L);

        //when
        when(cardDtoService.findById(anyLong())).thenReturn(cardDto);

        //then
        mockMvc.perform(get("/cards/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/cardform"))
                .andExpect(model().attributeExists("card"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/cards/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cards"));

        verify(cardDtoService, times(1)).deleteById(anyLong());
    }

}
