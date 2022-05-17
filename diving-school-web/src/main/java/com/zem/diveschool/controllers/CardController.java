package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.data.CardExtendedService;
import com.zem.diveschool.dto.CardDto;

import com.zem.diveschool.persistence.model.Card;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@Slf4j
@Controller
public class CardController {

    private static final String VIEWS_CARDS_INDEX = "cards/index";
    private static final String VIEWS_CARDS_SHOW = "cards/show";
    private static final String VIEWS_CARDS_CARDFORM = "cards/cardform";

    private static final String REDIRECT_CARDS = "redirect:/cards";

    private final CardExtendedService service;
    private final CardConverter converter;

    public CardController(CardExtendedService service,
                          CardConverter converter) {
        super();
        this.service = service;
        this.converter = converter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/cards", "/cards/index", "/cards/index.html", "cards.html"})
    public String listCards(Model model){
        Set<Card> cards = service.findAll();
        Set<CardDto> cardsDto = converter.convertFromEntities(cards);
        model.addAttribute("cards", cardsDto);
        return VIEWS_CARDS_INDEX;
    }

    @GetMapping({"/cards/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Optional<Card> cardOptional = service.findById(Long.valueOf(id));
        CardDto cardDto = converter.convertFromEntity(cardOptional.get());
        model.addAttribute("card", cardDto);
        return VIEWS_CARDS_SHOW;
    }

    @GetMapping("cards/new")
    public String newCard(Model model){
        model.addAttribute("card", CardDto.builder().build());
        return VIEWS_CARDS_CARDFORM;
    }

    @GetMapping("cards/{id}/update")
    public String updateCard(@PathVariable String id, Model model){
        Optional<Card> cardOptional = service.findById(Long.valueOf(id));
        CardDto cardDto = converter.convertFromEntity(cardOptional.get());
        model.addAttribute("card", cardDto);
        return  VIEWS_CARDS_CARDFORM;
    }

    @PostMapping("cards")
    public String saveOrUpdate(@ModelAttribute CardDto cardDto){
        Card card = converter.convertFromDto(cardDto);
        Card savedCard = service.save(card);
        CardDto savedCardDto = converter.convertFromEntity(savedCard);
        return REDIRECT_CARDS + "/" + savedCardDto.getId() + "/show";
    }

    @GetMapping("cards/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return REDIRECT_CARDS;
    }

    /* ---- */


    /* ---- */
}
