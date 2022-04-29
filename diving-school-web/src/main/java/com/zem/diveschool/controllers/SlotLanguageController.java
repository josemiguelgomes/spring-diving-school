package com.zem.diveschool.controllers;

import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class SlotLanguageController {

    private final SlotLanguageService slotLanguageService;

    public SlotLanguageController(SlotLanguageService slotLanguageService) {
        this.slotLanguageService = slotLanguageService;
    }

    @RequestMapping({"/slotLanguages", "/slotLanguages/index", "/slotLanguages/index.html", "slotLanguages.html"})
    public String listSlotLanguages(Model model){

        model.addAttribute("slotLanguages", slotLanguageService.findAll());

        return "slotLanguages/index";
    }

    @GetMapping("/api/slotLanguages")
    public @ResponseBody Set<SlotLanguage> getSlotJson(){
        return slotLanguageService.findAll();
    }

}
