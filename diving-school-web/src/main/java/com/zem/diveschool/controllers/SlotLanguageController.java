package com.zem.diveschool.controllers;

import com.zem.diveschool.data.SlotLanguageDtoService;
import com.zem.diveschool.dto.SlotLanguageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    /* --- */

    @GetMapping("/slots/{slotId}/slotlanguages")
    public String listSlotsSlotLanguage(@PathVariable String slotId, Model model){
        log.debug("Getting slot languages list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotlanguages", slotLanguageDtoService.findBySlotId(Long.valueOf(slotId)));
        return "slots/slotlanguages/list";
    }

    @GetMapping("/slots/{slotIdId}/slotLanguages/{slotLanguageId}/show")
    public String showSlotSlotLanguage(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot language id " + slotLanguageId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotlanguage", slotLanguageDtoService.findBySlotIdAndSlotLanguageId(Long.valueOf(slotId),
                Long.valueOf(slotLanguageId)));
        return "slots/slotLanguages/show";
    }
}
