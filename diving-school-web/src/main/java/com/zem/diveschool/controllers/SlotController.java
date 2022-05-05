package com.zem.diveschool.controllers;

import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.dto.SlotDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SlotController {

    private final SlotDtoService slotDtoService;

    public SlotController(SlotDtoService slotDtoService) {
        this.slotDtoService = slotDtoService;
    }

    @RequestMapping({"/slots", "/slots/index", "/slots/index.html", "slots.html"})
    public String listSlots(Model model){
        model.addAttribute("slots", slotDtoService.findAll());
        return "slots/index";
    }

    @RequestMapping({"/slots/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(id)));
        return "slots/show";
    }

    @GetMapping("slots/new")
    public String newSlot(Model model){
        model.addAttribute("slot", new SlotDto());
        return "slots/slotform";
    }

    @GetMapping("slots/{id}/update")
    public String updateSlot(@PathVariable String id, Model model){
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(id)));
        return  "slots/slotform";
    }

    @PostMapping("slots")
    public String saveOrUpdate(@ModelAttribute SlotDto slotDto){
        SlotDto savedSlotDto = slotDtoService.save(slotDto);
        return "redirect:/slots/" + savedSlotDto.getId() + "/show";
    }

    @GetMapping("slots/{id}/delete")
    public String deleteById(@PathVariable String id){
        slotDtoService.deleteById(Long.valueOf(id));
        return "redirect:/slots";
    }


    /* --- */

    @GetMapping("/slots/{slotId}/courses")
    public String listCoursesSlot(@PathVariable String slotId, Model model){
        log.debug("Getting courses list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/courses/list";
    }

    @GetMapping("/slots/{slotIdId}/courses/{courseId}/show")
    public String showCourseSlot(@PathVariable String slotId, @PathVariable String courseId, Model model){
        log.debug("Getting course id " + courseId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/courses/show";
    }

    @GetMapping("/slots/{slotId}/instructors")
    public String listInstructorsSlot(@PathVariable String slotId, Model model){
        log.debug("Getting instructors list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/instructors/list";
    }

    @GetMapping("/slots/{slotIdId}/instructors/{instructorId}/show")
    public String showInstructorSlot(@PathVariable String slotId, @PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/courses/show";
    }

    @GetMapping("/slots/{slotId}/locations")
    public String listLocationsSlot(@PathVariable String slotId, Model model){
        log.debug("Getting locations list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/locations/list";
    }

    @GetMapping("/slots/{slotIdId}/locations/{locationId}/show")
    public String showLocationSlot(@PathVariable String slotId, @PathVariable String locationId, Model model){
        log.debug("Getting location id " + locationId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/locations/show";
    }

    @GetMapping("/slots/{slotId}/slotlanguages")
    public String listSlotLanguagesSlot(@PathVariable String slotId, Model model){
        log.debug("Getting slot languages list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/slotlanguages/list";
    }

    @GetMapping("/slots/{slotIdId}/slotLanguages/{slotLanguageId}/show")
    public String showSlotLanguageSlot(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot language id " + slotLanguageId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/slotLanguages/show";
    }

    @GetMapping("/slots/{slotId}/students")
    public String listStudentsSlot(@PathVariable String slotId, Model model){
        log.debug("Getting students list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/students/list";
    }

    @GetMapping("/slots/{slotIdId}/students/{studentId}/show")
    public String showStudentSlot(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting student id " + studentId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(slotId)));
        return "slots/courses/show";
    }
}
