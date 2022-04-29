package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.services.CardService;
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

public class CardControllerTest {

    @Mock
    CardService cardService;


    @Mock
    ConvertObjectToObject<Card, CardDto> convertToDto;
    @Mock
    ConvertObjectToObject<CardDto, Card> convertToEntity;


    @Mock
    Model model;

    CardController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CardController(cardService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/index"));
    }

    @Test
    public void requestListCards() throws Exception {
        //given
        Set<CardDto> cardsDto = new HashSet<>();

        CardDto cardDto1 = new CardDto();
        cardDto1.setId(1L);
        cardsDto.add(cardDto1);

        CardDto cardDto2 = new CardDto();
        cardDto2.setId(2L);
        cardsDto.add(cardDto2);


        when(convertToDto.convert(cardService.findAll())).thenReturn(cardsDto);

        ArgumentCaptor<Set<CardDto>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.listCards(model);

        //then
        assertEquals("cards/index", viewName);
        verify(model, times(1)).addAttribute(eq("cards"), argumentCaptor.capture());
        Set<CardDto> setInController = argumentCaptor.getValue();
        assertEquals(cardsDto.size(), setInController.size());
    }

    @Test
    public void requestShowById() throws Exception {
        //given
        CardDto cardDto = new CardDto();
        cardDto.setId(1L);

        when(convertToDto.convert(cardService.findById(1L))).thenReturn(cardDto);

        ArgumentCaptor<CardDto> argumentCaptor = ArgumentCaptor.forClass(CardDto.class);

        //when
        String id = Long.valueOf(1L).toString();
        String viewName = controller.showById(id, model);

        //then
        assertEquals("cards/show", viewName);
        verify(model, times(1)).addAttribute(eq("card"), argumentCaptor.capture());
        CardDto inController = argumentCaptor.getValue();
        assertEquals(Optional.of(1L), Optional.ofNullable(inController.getId()));
    }


    @Test
    public void getNewCard() throws Exception {
        ///////// TODO
        //given

        //when

        //then
        assertEquals(1,0);
    }

    @Test
    public void getUpdateCard() throws Exception {
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
