package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class InstructorController {

    private final InstructorDtoService instructorDtoService;

    @Autowired
    private ConvertObjectToObject<Instructor, InstructorDto> convertToDto;
    @Autowired
    private ConvertObjectToObject<InstructorDto, Instructor> convertToEntity;

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
        model.addAttribute("location", instructorDto.getHomeAddress()); //TODO do a refactor on this

        return "instructors/show";
    }

    @GetMapping("instructors/new")
    public String newCard(Model model) {
        model.addAttribute("instructor", new InstructorDto());
        model.addAttribute("location", new LocationDto());

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

        return "redirect:/cards/" + savedInstructorDto.getId() + "/show";
    }

    @GetMapping("instructors/{id}/delete")
    public String deleteById(@PathVariable String id){
        instructorDtoService.deleteById(Long.valueOf(id));
        return "redirect:/instructors";
    }


    @GetMapping("/api/instructors")
    public @ResponseBody Set<InstructorDto> getInstructorJson(){
        return instructorDtoService.findAll();
    }

}
