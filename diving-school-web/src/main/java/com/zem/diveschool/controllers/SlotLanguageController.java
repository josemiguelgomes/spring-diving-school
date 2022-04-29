package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class SlotLanguageController {

    private final SlotLanguageService slotLanguageService;
    private final ConvertObjectToObject<SlotLanguage, SlotLanguageDto> convertToDto;
    private final ConvertObjectToObject<SlotLanguageDto, SlotLanguage> convertToEntity;

    public SlotLanguageController(SlotLanguageService slotLanguageService,
                                  ConvertObjectToObject<SlotLanguage, SlotLanguageDto> convertToDto,
                                  ConvertObjectToObject<SlotLanguageDto, SlotLanguage> convertToEntity) {
        this.slotLanguageService = slotLanguageService;
        this.convertToDto = convertToDto;
        this.convertToEntity = convertToEntity;
    }

    @RequestMapping({"/slotLanguages", "/slotLanguages/index", "/slotLanguages/index.html", "slotLanguages.html"})
    public String listSlotLanguages(Model model){
        model.addAttribute("slotLanguages", convertToDto.convert(slotLanguageService.findAll()));

        return "slotLanguages/index";
    }

    @RequestMapping({"/slotLanguages/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        SlotLanguage slotLanguage = slotLanguageService.findById(Long.valueOf(id));
        model.addAttribute("slotLanguage", convertToDto.convert(slotLanguage));

        return "slotLanguages/show";
    }

    @GetMapping("slotLanguages/new")
    public String newSlotLanguage(Model model){
        model.addAttribute("slotLanguage", new SlotLanguageDto());

        return "slotLanguages/slotLanguageform";
    }

    @GetMapping("slotLanguages/{id}/update")
    public String updateSlotLanguage(@PathVariable String id, Model model){
        model.addAttribute("slotLanguage", convertToDto.convert(slotLanguageService.findById(Long.valueOf(id))));
        return  "slotLanguages/slotLanguageform";
    }

    @PostMapping("slotLanguages")
    public String saveOrUpdate(@ModelAttribute SlotLanguageDto slotLanguageDto){
        SlotLanguage savedSlotLanguage = slotLanguageService.save(convertToEntity.convert(slotLanguageDto));
        return "redirect:/slotLanguages/" + savedSlotLanguage.getId() + "/show";
    }

    @GetMapping("slotLanguages/{id}/delete")
    public String deleteById(@PathVariable String id){
        slotLanguageService.deleteById(Long.valueOf(id));
        return "redirect:/slotLanguages";
    }

    @GetMapping("/api/slotLanguages")
    public @ResponseBody Set<SlotLanguageDto> getCardJson(){
        return convertToDto.convert(slotLanguageService.findAll());
    }
}
