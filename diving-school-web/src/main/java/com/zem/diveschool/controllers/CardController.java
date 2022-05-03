package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.persistence.model.Card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class CardController {

    private final CardDtoService cardDtoService;

    public CardController(CardDtoService cardDtoService) {
        this.cardDtoService = cardDtoService;
    }

    @RequestMapping({"/cards", "/cards/index", "/cards/index.html", "cards.html"})
    public String listCards(Model model){
        model.addAttribute("cards", cardDtoService.findAll());

        return "cards/index";
    }

    @RequestMapping({"/cards/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("card", cardDtoService.findById(Long.valueOf(id)));

        return "cards/show";
    }

    @GetMapping("cards/new")
    public String newCard(Model model){
        model.addAttribute("card", new CardDto());

        return "cards/cardform";
    }

    @GetMapping("cards/{id}/update")
    public String updateCard(@PathVariable String id, Model model){
        model.addAttribute("card", cardDtoService.findById(Long.valueOf(id)));
        return  "cards/cardform";
    }

    @PostMapping("cards")
    public String saveOrUpdate(@ModelAttribute CardDto cardDto){
        CardDto savedCardDto = cardDtoService.save(cardDto);
        return "redirect:/cards/" + savedCardDto.getId() + "/show";
    }

    @GetMapping("cards/{id}/delete")
    public String deleteById(@PathVariable String id){
        cardDtoService.deleteById(Long.valueOf(id));
        return "redirect:/cards";
    }

    @GetMapping("/api/cards")
    public @ResponseBody Set<CardDto> getCardJson(){
        return cardDtoService.findAll();
    }

}
