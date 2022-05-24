package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.SlotLanguageConverter;
import com.zem.diveschool.data.SlotLanguageExtendedService;
import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class SlotLanguageController {

    private static final String VIEWS_SLOTLANGUAGES_INDEX = "slotLanguages/index";
    private static final String VIEWS_SLOTLANGUAGES_INDEX_SHOW = "slotLanguages/show";
    private static final String VIEWS_SLOTLANGUAGES_SLOTLANGUAGEFORM = "slotLanguages/slotLanguageform";

    private static final String REDIRECT_SLOTLANGUAGES = "redirect:/slotLanguages";

    private final SlotLanguageExtendedService service;
    private final SlotLanguageConverter converter;

    public SlotLanguageController(SlotLanguageExtendedService service,
                                  SlotLanguageConverter converter) {
        this.service = service;
        this.converter = converter;
    }

//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    @GetMapping({"/slotLanguages", "/slotLanguages/index", "/slotLanguages/index.html", "slotLanguages.html"})
    public String listSlotLanguages(Model model){
        Set<SlotLanguage> slotLanguages = service.findAll();
        Set<SlotLanguageDto> slotLanguagesDto = converter.convertFromEntities(slotLanguages);
        model.addAttribute("slotLanguages", slotLanguagesDto);
        return VIEWS_SLOTLANGUAGES_INDEX;
    }

    @GetMapping({"/slotLanguages/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Optional<SlotLanguage> slotLanguageOptional = service.findById(Long.valueOf(id));
        SlotLanguageDto slotLanguageDto = converter.convertFromEntity(slotLanguageOptional.get());
        model.addAttribute("slotLanguage", slotLanguageDto);
        return VIEWS_SLOTLANGUAGES_INDEX_SHOW;
    }

    @GetMapping("slotLanguages/new")
    public String newSlotLanguage(Model model){
        model.addAttribute("slotLanguage", SlotLanguageDto.builder().build());
        return VIEWS_SLOTLANGUAGES_SLOTLANGUAGEFORM;
    }

    @GetMapping("slotLanguages/{id}/update")
    public String updateSlotLanguage(@PathVariable String id, Model model){
        Optional<SlotLanguage> slotLanguage = service.findById(Long.valueOf(id));
        SlotLanguageDto slotLanguageDto = converter.convertFromEntity(slotLanguage.get());
        model.addAttribute("slotLanguage", slotLanguageDto);
        return  VIEWS_SLOTLANGUAGES_SLOTLANGUAGEFORM;
    }

    @PostMapping("slotLanguages")
    public String saveOrUpdate(@ModelAttribute SlotLanguageDto slotLanguageDto){
        SlotLanguage slotLanguage = converter.convertFromDto(slotLanguageDto);
        SlotLanguage savedSlotLanguage = service.save(slotLanguage);
        SlotLanguageDto savedSlotLanguageDto = converter.convertFromEntity(savedSlotLanguage);
        return REDIRECT_SLOTLANGUAGES + "/" + savedSlotLanguageDto.getId() + "/show";
    }

    @GetMapping("slotLanguages/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return REDIRECT_SLOTLANGUAGES;
    }

    /* --- */


    /* --- */

}
