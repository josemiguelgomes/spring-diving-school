package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.services.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class InstructorController {

    private final InstructorService instructorService;
    private final ConvertObjectToObject<Instructor, InstructorDto> convertToDto;
    private final ConvertObjectToObject<InstructorDto, Instructor> convertToEntity;

    public InstructorController(InstructorService instructorService,
                                ConvertObjectToObject<Instructor, InstructorDto> convertToDto,
                                ConvertObjectToObject<InstructorDto, Instructor> convertToEntity) {
        this.instructorService = instructorService;
        this.convertToDto = convertToDto;
        this.convertToEntity = convertToEntity;
    }

    @RequestMapping({"/instructors", "/instructors/index", "/instructors/index.html", "instructors.html"})
    public String listInstructors(Model model) {
        model.addAttribute("instructors", convertToDto.convert(instructorService.findAll()));

        return "instructors/index";
    }

    @RequestMapping({"/instructors/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        Instructor instructor = instructorService.findById(Long.valueOf(id));
        model.addAttribute("instructor", convertToDto.convert(instructor));
        model.addAttribute("location", instructor.getHomeAddress()); //TODO do a refactor on this

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
        model.addAttribute("instructor", convertToDto.convert(instructorService.findById(Long.valueOf(id))));

        return  "instructors/instructorform";
    }

    @PostMapping("instructors")
    public String saveOrUpdate(@ModelAttribute InstructorDto instructorDto) {
        Instructor savedInstructor = instructorService.save(convertToEntity.convert(instructorDto));
        return "redirect:/cards/" + savedInstructor.getId() + "/show";
    }

    @GetMapping("instructors/{id}/delete")
    public String deleteById(@PathVariable String id){
        instructorService.deleteById(Long.valueOf(id));
        return "redirect:/instructors";
    }


    @GetMapping("/api/instructors")
    public @ResponseBody Set<Instructor> getInstructorJson(){
        return instructorService.findAll();
    }

}
