package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
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

    private final StudentService studentService;
    private final CardService cardService;
    private final SlotService slotService;
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

    public StudentController(StudentService studentService, CardService cardService, SlotService slotService) {
        this.studentService = studentService;
        this.cardService = cardService;
        this.slotService = slotService;
    }


    @RequestMapping({"/students", "/students/index", "/students/index.html", "students.html"})
    public String listStudents(Model model){
        model.addAttribute("students", convertToDto.convert(studentService.findAll()));

        return "students/index";
    }

    @RequestMapping({"/students/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Student student = studentService.findById(Long.valueOf(id));
        model.addAttribute("student", convertToDto.convert(student));

        model.addAttribute("location", student.getHomeAddress()); //TODO do a refactor on this

        Set <Card> cards = cardService.findByStudentID(student.getId());
        model.addAttribute("cards", convertCardToDto.convert(cards));

        Set <Slot> slots = slotService.findByStudentID(student.getId());
        model.addAttribute("slots", convertSlotToDto.convert(slots));

        return "students/show";
    }

    @GetMapping("students/new")
    public String newStudent(Model model){
        model.addAttribute("student", new StudentDto());
        model.addAttribute("location", new LocationDto());

        return "students/studentform";
    }

    @GetMapping("students/{id}/update")
    public String updateStudent(@PathVariable String id, Model model){
        model.addAttribute("student", convertToDto.convert(studentService.findById(Long.valueOf(id))));
        //TODO do I need to update Location ?????
        return  "students/studentform";
    }

    @PostMapping("students")
    public String saveOrUpdate(@ModelAttribute StudentDto studentDto, @ModelAttribute LocationDto locationDto ){
        Student savedStudent = studentService.save(convertToEntity.convert(studentDto));
        //Location savedLocation = locationService.save(convertToEntity.convert(locationDto));  // TODO
        return "redirect:/students/" + savedStudent.getId() + "/show";
    }

    @GetMapping("students/{id}/delete")
    public String deleteById(@PathVariable String id){
        // Delete the link Student / Slot
        Student student = studentService.findById(Long.valueOf(id));
        for (Slot slot :student.getSlots()) {
            slot.deleteStudent(student);
        }
        // Delete the student
        studentService.deleteById(Long.valueOf(id));
        return "redirect:/students";
    }

    @GetMapping("/api/students")
    public @ResponseBody Set<StudentDto> getStudentJson(){
        return convertToDto.convert(studentService.findAll());
    }

}
