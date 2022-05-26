package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.*;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.*;

import com.zem.diveschool.persistence.model.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class SlotController {

    private static final String VIEWS_SLOTS_INDEX = "slots/index";
    private static final String VIEWS_SLOTS_SHOW = "slots/show";
    private static final String VIEWS_SLOTS_SLOTFORM = "slots/slotform";
    private static final String VIEWS_SLOTS_FIND = "slots/find";

    private static final String VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM = "slots/instructors/instructorform";

    private static final String VIEWS_SLOTS_SLOTLANGUAGES_LIST = "slots/slotLanguages/list";
    private static final String VIEWS_SLOTS_SLOTLANGUAGES_SLOTLANGUAGEFORM = "slots/slotLanguages/slotLanguageform";
    private static final String VIEWS_SLOTS_SLOTLANGUAGES_SHOW = "slots/slotLanguages/show";

    private static final String REDIRECT_SLOTS = "redirect:/slots";
    private static final String REDIRECT_SLOTS_SLOTLANGUAGES = "redirect:/slots/slotLanguages";

    private final SlotExtendedService slotService;

    private final SlotConverter slotConverter;
    private final InstructorConverter instructorConverter;
    private final SlotLanguageConverter slotLanguageConverter;

    public SlotController(SlotExtendedService slotService,
                          SlotConverter slotConverter,
                          InstructorConverter instructorConverter,
                          SlotLanguageConverter slotLanguageConverter) {
        this.slotService = slotService;
        this.slotConverter = slotConverter;
        this.instructorConverter = instructorConverter;
        this.slotLanguageConverter = slotLanguageConverter;
    }

//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    @GetMapping({"/slots", "/slots/index", "/slots/index.html", "slots.html"})
    public String listSlots(@NotNull Model model){
        Set<Slot> slots = slotService.findAll();
        Set<SlotDto> slotsDto = slotConverter.convertFromEntities(slots);
        model.addAttribute("slots", slotsDto);
        return VIEWS_SLOTS_INDEX;
    }

    @GetMapping({"/slots/{id}/show"})
    public String showById(@PathVariable String id, @NotNull Model model) {
        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(id));
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SHOW;
    }

    @GetMapping("slots/new")
    public String newSlot(@NotNull Model model){
        model.addAttribute("slot", SlotDto.builder().build());
        return VIEWS_SLOTS_SLOTFORM;
    }

    @GetMapping("slots/{id}/update")
    public String updateSlot(@PathVariable String id, @NotNull Model model){
        Optional<Slot> slot = slotService.findById(Long.valueOf(id));
        SlotDto slotDto = slotConverter.convertFromEntity(slot.get());
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SLOTFORM;
    }

    @PostMapping("slots")
    public String saveOrUpdate(@ModelAttribute SlotDto slotDto){
        Slot slot = slotConverter.convertFromDto(slotDto);
        Slot savedSlot = slotService.save(slot);
        SlotDto savedSlotDto = slotConverter.convertFromEntity(savedSlot);
        return REDIRECT_SLOTS + "/" + savedSlotDto.getId() + "/show";
    }

    @GetMapping("slots/{id}/delete")
    public String deleteById(@PathVariable String id){
        slotService.deleteById(Long.valueOf(id));
        return "redirect:/slots";
    }

    /* --- */

    @RequestMapping("/slots/find")
    public String findSlots(Model model) {
        Set<Slot> slots = slotService.findAll();
        Set<SlotDto> slotsDto = slotConverter.convertFromEntities(slots);
        model.addAttribute("slots", slotsDto);
        return VIEWS_SLOTS_FIND;
    }

    /* --- */





    @GetMapping("/slots/{slotId}/instructors/new")
    public String newSlotInstructor(@PathVariable String slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        Instructor instructor = new Instructor();
        slotOptional.ifPresent(slot -> slot.getInstructors().add(instructor));

        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructor);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructor", instructorDto);
        return VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM;
    }


    @GetMapping("/slots/{slotId}/slotlanguages")
    public String listSlotsSlotLanguages(@PathVariable String slotId, Model model){
        log.debug("Getting slot languages list for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        Set<SlotLanguage> slotLanguages = slotService.findLanguagesBySlotId(Long.valueOf(slotId));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        Set<SlotLanguageDto> slotLanguagesDto = slotLanguageConverter.convertFromEntities(slotLanguages);


        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotLanguages", slotLanguagesDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SLOTLANGUAGES_LIST;
    }

    @GetMapping("/slots/{slotId}/slotLanguages/new")
    public String newSlotSlotLanguage(@PathVariable String slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        SlotLanguage slotLanguage = new SlotLanguage();
        slotOptional.ifPresent(slot -> slot.getLanguages().add(slotLanguage));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        SlotLanguageDto slotLanguageDto = slotLanguageConverter.convertFromEntity(slotLanguage);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotLanguage", slotLanguageDto);
        return VIEWS_SLOTS_SLOTLANGUAGES_SLOTLANGUAGEFORM;
    }

    @GetMapping("/slots/{slotId}/slotlanguages/{slotLanguageId}/delete")
    public String deleteSlotSlotLanguage(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot id: " + slotId + " and Language Id: " + slotLanguageId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        slotService.deleteBySlotIdAndSlotLanguageId(Long.valueOf(slotId), Long.valueOf(slotLanguageId));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDto);
        return REDIRECT_SLOTS_SLOTLANGUAGES;
    }

    @GetMapping("/slots/{slotId}/slotlanguages/{slotLanguageId}/show")
    public String showSlotSlotLanguage(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot language id " + slotLanguageId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        Optional<SlotLanguage> slotLanguageOptional =
                slotService.findBySlotIdAndSlotLanguageId(Long.valueOf(slotId), Long.valueOf(slotLanguageId));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        SlotLanguageDto slotLanguageDto = slotLanguageConverter.convertFromEntity(slotLanguageOptional.get());

        model.addAttribute("slotLanguage", slotLanguageDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SLOTLANGUAGES_SHOW;
    }

}
