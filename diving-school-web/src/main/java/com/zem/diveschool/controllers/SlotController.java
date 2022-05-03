package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@Controller
public class SlotController {

    private final SlotDtoService slotDtoService;
    private final CourseDtoService courseDtoService;

    public SlotController(SlotDtoService slotDtoService,
                          CourseDtoService courseDtoService) {
        this.slotDtoService = slotDtoService;
        this.courseDtoService = courseDtoService;
    }

    @RequestMapping({"/slots", "/slots/index", "/slots/index.html", "slots.html"})
    public String listSlots(Model model){
        model.addAttribute("slots", slotDtoService.findAll());

        return "slots/index";
    }

    @RequestMapping({"/slots/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        SlotDto slotDto = slotDtoService.findById(Long.valueOf(id));
        model.addAttribute("slot", slotDto);

        CourseDto courseDto = courseDtoService.findById(slotDto.getCourse().getId());
        model.addAttribute("course", courseDto);

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
        model.addAttribute("slot", slotDtoService.findById(Long.valueOf(id)));
//        model. ..... // TODO
        return  "slots/slotform";
    }

    @PostMapping("slots")
    public String saveOrUpdate(@ModelAttribute SlotDto slotDto){
        SlotDto savedSlotDto = slotDtoService.save(slotDto);
        //TODO ???
        return "redirect:/slots/" + savedSlotDto.getId() + "/show";
    }

    @GetMapping("slots/{id}/delete")
    public String deleteById(@PathVariable String id){
        // TODO ???????
//        location.delete ..  // TODO.
        slotDtoService.deleteById(Long.valueOf(id));
        return "redirect:/slots";
    }

    @GetMapping("/api/slots")
    public @ResponseBody Set<SlotDto> getSlotJson(){
        return slotDtoService.findAll();
    }
}
