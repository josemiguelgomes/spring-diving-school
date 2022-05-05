package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.dto.CardDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
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

    @GetMapping("/students/{studentId}/cards")
    public String listStudentCards(@PathVariable String studentId, Model model){
        log.debug("Getting cards list for student id: " + studentId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("cards", cardDtoService.findByStudentID(Long.valueOf(studentId)));
        return "students/cards/list";
    }

    @GetMapping("/students/{studentId}/cards/{cardId}/show")
    public String showStudentCard(@PathVariable String studentId, @PathVariable String cardId, Model model){
        log.debug("Getting card id " + cardId + " for student id: " + studentId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("card", cardDtoService.findByStudentIdAndCardId(Long.valueOf(studentId),
                Long.valueOf(studentId)));
        return "students/cards/show";
    }

}
