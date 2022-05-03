package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.data.CardDtoService;
import com.zem.diveschool.data.SlotDtoService;
import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import com.zem.diveschool.persistence.services.CardService;
import com.zem.diveschool.persistence.services.SlotService;
import com.zem.diveschool.persistence.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class StudentController {

    private final StudentDtoService studentDtoService;
    private final CardDtoService cardDtoService;
    private final SlotDtoService slotDtoService;
    @Autowired
    private ConvertObjectToObject<Student, StudentDto> convertToDto;
    @Autowired
    private ConvertObjectToObject<StudentDto, Student> convertToEntity;
    @Autowired
    private ConvertObjectToObject<Card, CardDto> convertCardToDto;
    @Autowired
    private ConvertObjectToObject<CardDto, Card> convertCardToEntity;
    @Autowired
    private ConvertObjectToObject<Slot, SlotDto> convertSlotToDto;
    @Autowired
    private ConvertObjectToObject<SlotDto, Slot> convertSlotToEntity;

    public StudentController(StudentDtoService studentDtoService,
                             CardDtoService cardDtoService,
                             SlotDtoService slotDtoService) {
        this.studentDtoService = studentDtoService;
        this.cardDtoService = cardDtoService;
        this.slotDtoService = slotDtoService;
    }


    @RequestMapping({"/students", "/students/index", "/students/index.html", "students.html"})
    public String listStudents(Model model){
        model.addAttribute("students", studentDtoService.findAll());

        return "students/index";
    }

    @RequestMapping({"/students/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        StudentDto studentDto = studentDtoService.findById(Long.valueOf(id));
        model.addAttribute("student", studentDto);

        model.addAttribute("location", studentDto.getHomeAddress()); //TODO do a refactor on this

        Set <CardDto> cardsDto = cardDtoService.findByStudentID(studentDto.getId());
        model.addAttribute("cards", cardsDto);

        Set <SlotDto> slotsDto = slotDtoService.findByStudentID(studentDto.getId());
        model.addAttribute("slots", slotsDto);

        return "students/show";
    }

    @GetMapping("students/new")
    public String newStudent(Model model){
        model.addAttribute("student", new StudentDto());
        model.addAttribute("location", new LocationDto());  // TODO ???

        return "students/studentform";
    }

    @GetMapping("students/{id}/update")
    public String updateStudent(@PathVariable String id, Model model){
        model.addAttribute("student", studentDtoService.findById(Long.valueOf(id)));
        //TODO do I need to update Location ?????
        return  "students/studentform";
    }

    @PostMapping("students")
    public String saveOrUpdate(@ModelAttribute StudentDto studentDto, @ModelAttribute LocationDto locationDto ){
        StudentDto savedStudentDto = studentDtoService.save(studentDto);
        //LocationDto savedLocation = locationDtoService.save(locationDto));  // TODO
        return "redirect:/students/" + savedStudentDto.getId() + "/show";
    }

    @GetMapping("students/{id}/delete")
    public String deleteById(@PathVariable String id){
        // Delete the link Student / Slot
        StudentDto studentDto = studentDtoService.findById(Long.valueOf(id));
        for (SlotDto slotDto :studentDto.getSlots()) {
            slotDtoService.delete(slotDto);
        }
        // Delete the student
        studentDtoService.deleteById(Long.valueOf(id));
        return "redirect:/students";
    }

    @GetMapping("/api/students")
    public @ResponseBody Set<StudentDto> getStudentJson(){
        return studentDtoService.findAll();
    }

}
