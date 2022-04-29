package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.services.CardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class CardController {

    private final CardService cardService;
    private final ConvertObjectToObject<Card, CardDto> convertToDto;
    private final ConvertObjectToObject<CardDto, Card> convertToEntity;


    public CardController(CardService cardService,
                          ConvertObjectToObject<Card, CardDto> convertToDto,
                          ConvertObjectToObject<CardDto, Card> convertToEntity) {
        this.cardService = cardService;
        this.convertToDto = convertToDto;
        this.convertToEntity = convertToEntity;
    }

    @RequestMapping({"/cards", "/cards/index", "/cards/index.html", "cards.html"})
    public String listCards(Model model){
        model.addAttribute("cards", convertToDto.convert(cardService.findAll()));

        return "cards/index";
    }

    @RequestMapping({"/cards/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Card card = cardService.findById(Long.valueOf(id));
        model.addAttribute("card", convertToDto.convert(card));

        return "cards/show";
    }

    @GetMapping("cards/new")
    public String newCard(Model model){
        model.addAttribute("card", new CardDto());

        return "cards/cardform";
    }

    @GetMapping("cards/{id}/update")
    public String updateCard(@PathVariable String id, Model model){
        model.addAttribute("card", convertToDto.convert(cardService.findById(Long.valueOf(id))));
        return  "cards/cardform";
    }

    @PostMapping("cards")
    public String saveOrUpdate(@ModelAttribute CardDto cardDto){
        Card savedCard = cardService.save(convertToEntity.convert(cardDto));
        return "redirect:/cards/" + savedCard.getId() + "/show";
    }

    @GetMapping("cards/{id}/delete")
    public String deleteById(@PathVariable String id){
        cardService.deleteById(Long.valueOf(id));
        return "redirect:/cards";
    }

    @GetMapping("/api/cards")
    public @ResponseBody Set<CardDto> getCardJson(){
        return convertToDto.convert(cardService.findAll());
    }

}
