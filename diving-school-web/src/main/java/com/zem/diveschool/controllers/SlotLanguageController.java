package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.data.SlotLanguageDtoService;
import com.zem.diveschool.dto.SlotLanguageDto;
import com.zem.diveschool.persistence.model.SlotLanguage;
import com.zem.diveschool.persistence.services.SlotLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class SlotLanguageController {

    private final SlotLanguageDtoService slotLanguageDtoService;

    public SlotLanguageController(SlotLanguageDtoService slotLanguageDtoService) {
        this.slotLanguageDtoService = slotLanguageDtoService;
    }

    @RequestMapping({"/slotLanguages", "/slotLanguages/index", "/slotLanguages/index.html", "slotLanguages.html"})
    public String listSlotLanguages(Model model){
        model.addAttribute("slotLanguages", slotLanguageDtoService.findAll());

        return "slotLanguages/index";
    }

    @RequestMapping({"/slotLanguages/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("slotLanguage", slotLanguageDtoService.findById(Long.valueOf(id)));

        return "slotLanguages/show";
    }

    @GetMapping("slotLanguages/new")
    public String newSlotLanguage(Model model){
        model.addAttribute("slotLanguage", new SlotLanguageDto());

        return "slotLanguages/slotLanguageform";
    }

    @GetMapping("slotLanguages/{id}/update")
    public String updateSlotLanguage(@PathVariable String id, Model model){
        model.addAttribute("slotLanguage", slotLanguageDtoService.findById(Long.valueOf(id)));
        return  "slotLanguages/slotLanguageform";
    }

    @PostMapping("slotLanguages")
    public String saveOrUpdate(@ModelAttribute SlotLanguageDto slotLanguageDto){
        SlotLanguageDto savedSlotLanguageDto = slotLanguageDtoService.save(slotLanguageDto);
        return "redirect:/slotLanguages/" + savedSlotLanguageDto.getId() + "/show";
    }

    @GetMapping("slotLanguages/{id}/delete")
    public String deleteById(@PathVariable String id){
        slotLanguageDtoService.deleteById(Long.valueOf(id));
        return "redirect:/slotLanguages";
    }

    @GetMapping("/api/slotLanguages")
    public @ResponseBody Set<SlotLanguageDto> getCardJson(){
        return slotLanguageDtoService.findAll();
    }
}
