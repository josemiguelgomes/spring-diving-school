package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;
import com.zem.diveschool.persistence.services.CourseService;
import com.zem.diveschool.persistence.services.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@Controller
public class SlotController {

    private final SlotService slotService;
    private final CourseService courseService;
    @Autowired
    private ConvertObjectToObject<Slot, SlotDto> convertToDto;
    @Autowired
    private ConvertObjectToObject<SlotDto, Slot> convertToEntity;
    @Autowired
    private ConvertObjectToObject<Course, CourseDto> convertCourseToDto;
    @Autowired
    private ConvertObjectToObject<CourseDto, Course> convertCourseToEntity;


    public SlotController(SlotService slotService,
                          CourseService courseService) {
        this.slotService = slotService;
        this.courseService = courseService;
    }

    @RequestMapping({"/slots", "/slots/index", "/slots/index.html", "slots.html"})
    public String listSlots(Model model){
        model.addAttribute("slots", slotService.findAll());

        return "slots/index";
    }

    @RequestMapping({"/slots/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Slot slot = slotService.findById(Long.valueOf(id));
        model.addAttribute("slot", convertToDto.convert(slot));

        Course course = courseService.findById(slot.getCourse().getId());
        model.addAttribute("course", convertCourseToDto.convert(course));

//        List<SlotLanguage> languages = slotLanguageServices.findAll();  ???? // TODO

//        List<Student> students = studentsServices.findAll();  ???? // TODO

//        List<Instructor> instructors= instructorsServices.findAll();  ???? // TODO

        return "slots/show";
    }

    @GetMapping("slots/new")
    public String newSlot(Model model){
        model.addAttribute("slot", new SlotDto());
        model.addAttribute("location", new LocationDto());
        model.addAttribute("course", new CourseDto());
        model.addAttribute("slotLanguages", new ArrayList<SlotLanguage>(5));  // TODO
        model.addAttribute("students", new ArrayList<Student>(5));  // TODO
        model.addAttribute("instructors", new ArrayList<Instructor>(5));  // TODO

        return "slots/slotform";
    }

    @GetMapping("slots/{id}/update")
    public String updateSlot(@PathVariable String id, Model model){
        model.addAttribute("slot", convertToDto.convert(slotService.findById(Long.valueOf(id))));
//        model. ..... // TODO
        return  "slots/slotform";
    }

    @PostMapping("slots")
    public String saveOrUpdate(@ModelAttribute SlotDto slotDto){
        Slot savedSlot = slotService.save(convertToEntity.convert(slotDto));
        //TODO ???
        return "redirect:/slots/" + savedSlot.getId() + "/show";
    }

    @GetMapping("slots/{id}/delete")
    public String deleteById(@PathVariable String id){
        // TODO ???????
//        location.delete ..  // TODO.
        slotService.deleteById(Long.valueOf(id));
        return "redirect:/slots";
    }

    @GetMapping("/api/slots")
    public @ResponseBody Set<SlotDto> getSlotJson(){
        return convertToDto.convert(slotService.findAll());
    }

}
