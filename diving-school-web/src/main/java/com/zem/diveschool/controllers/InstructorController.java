package com.zem.diveschool.controllers;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.InstructorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class InstructorController {

    private final InstructorDtoService instructorDtoService;

    public InstructorController(InstructorDtoService instructorDtoService) {
        this.instructorDtoService = instructorDtoService;
    }

    @RequestMapping({"/instructors", "/instructors/index", "/instructors/index.html", "instructors.html"})
    public String listInstructors(Model model) {
        model.addAttribute("instructors", instructorDtoService.findAll());
        return "instructors/index";
    }

    @RequestMapping({"/instructors/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        InstructorDto instructorDto = instructorDtoService.findById(Long.valueOf(id));
        model.addAttribute("instructor", instructorDto);
        return "instructors/show";
    }

    @GetMapping("instructors/new")
    public String newInstructor(Model model) {
        model.addAttribute("instructor", new InstructorDto());
        return "instructors/instructorform";
    }

    @GetMapping("instructors/{id}/update")
    public String updateInstructor(@PathVariable String id, Model model) {
        model.addAttribute("instructor", instructorDtoService.findById(Long.valueOf(id)));
        return  "instructors/instructorform";
    }

    @PostMapping("instructors")
    public String saveOrUpdate(@ModelAttribute InstructorDto instructorDto) {
        InstructorDto savedInstructorDto = instructorDtoService.save(instructorDto);
        return "redirect:/instructors/" + savedInstructorDto.getId() + "/show";
    }

    @GetMapping("instructors/{id}/delete")
    public String deleteById(@PathVariable String id){
        instructorDtoService.deleteById(Long.valueOf(id));
        return "redirect:/instructors";
    }

    /* --- */

    @GetMapping("/slots/{slotId}/instructors")
    public String listSlotInstructors(@PathVariable String slotId, Model model){
        log.debug("Getting instructors list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructors", instructorDtoService.findBySlotId(Long.valueOf(slotId)));
        return "slots/instructors/list";
    }

    @GetMapping("/slots/{slotIdId}/instructors/{instructorId}/show")
    public String showSlotInstructor(@PathVariable String slotId, @PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructor", instructorDtoService.findBySlotIdAndInstructorId(Long.valueOf(slotId),
                Long.valueOf(instructorId)));
        return "slots/instructors/show";
    }

}
