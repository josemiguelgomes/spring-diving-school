package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@Controller
public class StudentController {

    private final StudentDtoService studentDtoService;


    public StudentController(StudentDtoService studentDtoService) {
        this.studentDtoService = studentDtoService;
    }

    @RequestMapping({"/students", "/students/index", "/students/index.html", "students.html"})
    public String listStudents(Model model){
        model.addAttribute("students", studentDtoService.findAll());
        return "students/index";
    }

    @RequestMapping({"/students/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("student", studentDtoService.findById(Long.valueOf(id)));
        return "students/show";
    }

    @GetMapping("students/new")
    public String newStudent(Model model){
        model.addAttribute("student", new StudentDto());
        return "students/studentform";
    }

    @GetMapping("students/{id}/update")
    public String updateStudent(@PathVariable String id, Model model){
        model.addAttribute("student", studentDtoService.findById(Long.valueOf(id)));
        return  "students/studentform";
    }

    @PostMapping("students")
    public String saveOrUpdate(@ModelAttribute StudentDto studentDto, @ModelAttribute LocationDto locationDto ){
        StudentDto savedStudentDto = studentDtoService.save(studentDto);
        return "redirect:/students/" + savedStudentDto.getId() + "/show";
    }

    @GetMapping("students/{id}/delete")
    public String deleteById(@PathVariable String id){
        studentDtoService.deleteById(Long.valueOf(id));
        return "redirect:/students";
    }


    /* --- */

    @GetMapping("/slots/{slotId}/students")
    public String listSlotStudent(@PathVariable String slotId, Model model){
        log.debug("Getting students list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("students", studentDtoService.findBySlotId(Long.valueOf(slotId)));
        return "slots/students/list";
    }

    @GetMapping("/slots/{slotIdId}/students/{studentId}/show")
    public String showSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting student id " + studentId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("student", studentDtoService.findBySlotIdAndStudentId(Long.valueOf(slotId),
                Long.valueOf(studentId)));
        return "slots/students/show";
    }
}
