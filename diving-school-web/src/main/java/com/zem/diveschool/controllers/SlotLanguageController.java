package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.SlotLanguageConverter;
import com.zem.diveschool.data.SlotLanguageExtendedService;
import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class SlotLanguageController {

    private final SlotLanguageExtendedService service;
    private final SlotLanguageConverter converter;

    public SlotLanguageController(SlotLanguageExtendedService service,
                                  SlotLanguageConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping({"/slotLanguages", "/slotLanguages/index", "/slotLanguages/index.html", "slotLanguages.html"})
    public String listSlotLanguages(Model model){
        Set<SlotLanguage> slotLanguages = service.findAll();
        Set<SlotLanguageDto> slotLanguagesDto = converter.convertFromEntities(slotLanguages);
        model.addAttribute("slotLanguages", slotLanguagesDto);
        return "slotLanguages/index";
    }

    @GetMapping({"/slotLanguages/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Optional<SlotLanguage> slotLanguageOptional = service.findById(Long.valueOf(id));
        SlotLanguageDto slotLanguageDto = converter.convertFromEntity(slotLanguageOptional.get());
        model.addAttribute("slotLanguage", slotLanguageDto);
        return "slotLanguages/show";
    }

    @GetMapping("slotLanguages/new")
    public String newSlotLanguage(Model model){
        model.addAttribute("slotLanguage", new SlotLanguageDto());
        return "slotLanguages/slotLanguageform";
    }

    @GetMapping("slotLanguages/{id}/update")
    public String updateSlotLanguage(@PathVariable String id, Model model){
        Optional<SlotLanguage> slotLanguage = service.findById(Long.valueOf(id));
        SlotLanguageDto slotLanguageDto = converter.convertFromEntity(slotLanguage.get());
        model.addAttribute("slotLanguage", slotLanguageDto);
        return  "slotLanguages/slotLanguageform";
    }

    @PostMapping("slotLanguages")
    public String saveOrUpdate(@ModelAttribute SlotLanguageDto slotLanguageDto){
        SlotLanguage slotLanguage = converter.convertFromDto(slotLanguageDto);
        SlotLanguage savedSlotLanguage = service.save(slotLanguage);
        SlotLanguageDto savedSlotLanguageDto = converter.convertFromEntity(savedSlotLanguage);
        return "redirect:/slotLanguages/" + savedSlotLanguageDto.getId() + "/show";
    }

    @GetMapping("slotLanguages/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return "redirect:/slotLanguages";
    }

    /* --- */


    /* --- */

}
