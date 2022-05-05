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

    @GetMapping("/courses/{courseId}/slots")
    public String listCourseSlots(@PathVariable String courseId, Model model){
        log.debug("Getting slots list for course id: " + courseId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotDtoService.findByCourseId(Long.valueOf(courseId)));
        return "courses/slots/list";
    }

    @GetMapping("/courses/{courseId}/slots/{slotId}/show")
    public String showCourseSlots(@PathVariable String courseId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for course id: " + courseId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findByCourseIdAndSlotId(Long.valueOf(courseId),
                Long.valueOf(slotId)));
        return "courses/slots/show";
    }

    @GetMapping("/instructors/{instructorId}/slots")
    public String listInstructorSlots(@PathVariable String instructorId, Model model){
        log.debug("Getting slots list for instructor id: " + instructorId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotDtoService.findByInstructorId(Long.valueOf(instructorId)));
        return "instructors/slots/list";
    }

    @GetMapping("/instructors/{instructorId}/slots/{slotId} ")
    public String listInstructorSlot(@PathVariable String instructorId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for instructor id: " + instructorId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findByInstructorIdAndSlotId(Long.valueOf(instructorId),
                Long.valueOf(slotId)));
        return "instructors/slots/show";
    }

    @GetMapping("/students/{studentId}/slots")
    public String listStudentSlots(@PathVariable String studentId, Model model){
        log.debug("Getting slots list for student id: " + studentId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotDtoService.findByStudentId(Long.valueOf(studentId)));
        return "students/slots/list";
    }

    @GetMapping("/students/{studentId}/slots/{slotId}/show")
    public String showStudentSlot(@PathVariable String studentId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for student id: " + studentId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDtoService.findByStudentIdAndSlotId(Long.valueOf(studentId),
                Long.valueOf(slotId)));
        return "students/slots/show";
    }
}
