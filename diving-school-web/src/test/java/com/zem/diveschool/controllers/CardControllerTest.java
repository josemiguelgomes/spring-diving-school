package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.converters.impl.simple.StudentConverter;
import com.zem.diveschool.data.CardExtendedService;
import com.zem.diveschool.data.StudentExtendedService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
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

public class CardControllerTest {

    @Mock
    CardExtendedService service;

    @Mock
    StudentExtendedService studentService;

    @Mock
    CardConverter converter;

    @Mock
    StudentConverter studentConverter;

    CardController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CardController(service, studentService, converter, studentConverter);
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
    public void test_listCards() throws Exception {
        //given
        Set<Card> cards = new HashSet<>();
        Set<CardDto> cardsDto = new HashSet<>();

        //when
        when(service.findAll()).thenReturn(cards);
        when(converter.convertFromEntities(anyCollection())).thenReturn(cardsDto);

        //then
        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/index"))
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().size(1));

        verify(service, times(1)).findAll();
        verify(converter, times(1)).convertFromEntities(anyCollection());
    }

    @Test
    public void test_showById() throws Exception {
        //given
        Optional<Card> cardOptional = Optional.of(new Card());
        CardDto carDto = new CardDto();

        //when
        when(service.findById(anyLong())).thenReturn(cardOptional);
        when(converter.convertFromEntity(any(Card.class))).thenReturn(carDto);

        //then
        mockMvc.perform(get("/cards/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/show"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().size(1));

        verify(service, times(1)).findById(anyLong());
        verify(converter, times(1)).convertFromEntity(any(Card.class));
    }


    @Test
    public void test_newCard() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/cards/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/cardform"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().size(1));
    }

    @Test
    public void test_updateCard() throws Exception {
        //given
        Optional<Card> cardOptional = Optional.of(new Card());
        CardDto cardDto = new CardDto();

        //when
        when(service.findById(anyLong())).thenReturn(cardOptional);
        when(converter.convertFromEntity(any(Card.class))).thenReturn(cardDto);

        //then
        mockMvc.perform(get("/cards/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("cards/cardform"))
                .andExpect(model().attributeExists("card"))
                .andExpect(model().size(1));

        verify(service, times(1)).findById(anyLong());
        verify(converter, times(1)).convertFromEntity(any(Card.class));
    }

    @Test
    public void test_saveOrUpdate() throws Exception {
        //given
        Card card = new Card();
        Card savedCard = new Card();
        CardDto savedCardDto = new CardDto();

        //when
        when(converter.convertFromDto(any(CardDto.class))).thenReturn(card);
        when(service.save(any(Card.class))).thenReturn(savedCard);
        when(converter.convertFromEntity(any(Card.class))).thenReturn(savedCardDto);

        //then
        mockMvc.perform(post("/cards")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("card", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cards/2/show"))
                .andExpect(model().size(1)); //why ???

        verify(converter, times(1)).convertFromDto(any(CardDto.class));
        verify(service, times(1)).save(any(Card.class));
        verify(converter, times(1)).convertFromEntity(any(Card.class));
    }

    @Test
    public void test_deleteById() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/cards/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cards"))
                .andExpect(model().size(0));

        verify(service, times(1)).deleteById(anyLong());
    }

    /* ---- */

    /* ---- */

}
