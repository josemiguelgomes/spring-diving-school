package com.zem.diveschool.controllers;

import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.dto.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @RequestMapping("/slots/find")
    public String findSlots(Model model) {
        model.addAttribute("slots", slotDtoService.findAll());
        return "slots/find";
    }

    /* --- */

    @GetMapping("/slots/{slotId}/courses")
    public String listSlotCourses(@PathVariable String slotId, Model model){
        log.debug("Getting courses list for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Set<CourseDto> coursesDto = slotDtoService.findCoursesBySlotId(Long.valueOf(slotId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("courses", coursesDto);
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/courses/list";
    }

    @GetMapping("/slots/{slotIdId}/courses/{courseId}/show")
    public String showSlotCourse(@PathVariable String slotId, @PathVariable String courseId, Model model){
        log.debug("Getting course id " + courseId + " for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Optional<CourseDto> courseDtoOptional =
                slotDtoService.findBySlotIdAndCourseId(Long.valueOf(slotId), Long.valueOf(courseId));

        model.addAttribute("course", courseDtoOptional.orElse(null));
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/courses/show";
    }

    @GetMapping("/slots/{slotId}/instructors")
    public String listSlotInstructors(@PathVariable String slotId, Model model){
        log.debug("Getting instructors list for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Set<InstructorDto> instructorsDto = slotDtoService.findInstructorsBySlotId(Long.valueOf(slotId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructors", instructorsDto);
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/instructors/list";
    }

    @GetMapping("/slots/{slotIdId}/instructors/{instructorId}/show")
    public String showSlotInstructor(@PathVariable String slotId, @PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId + " for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Optional<InstructorDto> instructorDtoOptional =
                slotDtoService.findBySlotIdAndInstructorId(Long.valueOf(slotId), Long.valueOf(instructorId));

        model.addAttribute("instructor", instructorDtoOptional.orElse(null));
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/instructors/show";
    }

    @GetMapping("/slots/{slotId}/slotlanguages")
    public String listSlotsSlotLanguage(@PathVariable String slotId, Model model){
        log.debug("Getting slot languages list for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Set<SlotLanguageDto> slotLanguagesDto = slotDtoService.findLanguagesBySlotId(Long.valueOf(slotId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotLanguages", slotLanguagesDto);
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/slotLanguages/list";
    }

    @GetMapping("/slots/{slotIdId}/slotlanguages/{slotLanguageId}/show")
    public String showSlotSlotLanguage(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot language id " + slotLanguageId + " for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Optional<SlotLanguageDto> slotLanguageDtoOptional =
                slotDtoService.findBySlotIdAndSlotLanguageId(Long.valueOf(slotId), Long.valueOf(slotLanguageId));

        model.addAttribute("slotLanguage", slotLanguageDtoOptional.orElse(null));
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/slotLanguages/show";
    }

    @GetMapping("/slots/{slotId}/students")
    public String listSlotStudent(@PathVariable String slotId, Model model){
        log.debug("Getting students list for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Set<StudentDto> studentsDto = slotDtoService.findStudentsBySlotId(Long.valueOf(slotId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("students", studentsDto);
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/students/list";
    }

    @GetMapping("/slots/{slotId}/students/{studentId}/show")
    public String showSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting student id " + studentId + " for slot id: " + slotId);

        Optional<SlotDto> slotDtoOptional = slotDtoService.findById(Long.valueOf(slotId));
        Optional<StudentDto> studentDtoOptional =
                slotDtoService.findBySlotIdAndStudentId(Long.valueOf(slotId), Long.valueOf(studentId));

        model.addAttribute("student", studentDtoOptional.orElse(null));
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        return "slots/students/show";
    }
}
