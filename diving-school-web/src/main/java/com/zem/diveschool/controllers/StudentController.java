package com.zem.diveschool.controllers;

import com.zem.diveschool.data.StudentDtoService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    @RequestMapping("/students/find")
    public String findStudents(Model model) {
        model.addAttribute("students", studentDtoService.findAll());
        return "students/find";
    }

    /* --- */

    @GetMapping("/students/{studentId}/cards")
    public String listStudentCards(@PathVariable String studentId, Model model){
        log.debug("Getting cards list for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Set<CardDto> cardsDto = studentDtoService.findCardsByStudentId(Long.valueOf(studentId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("cards", cardsDto);
        model.addAttribute("student", studentDtoOptional.orElse(null));

        return "students/cards/list";
    }

    @GetMapping("/students/{studentId}/cards/{cardId}/show")
    public String showStudentCard(@PathVariable String studentId, @PathVariable String cardId, Model model){
        log.debug("Getting card id " + cardId + " for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Optional<CardDto> cardDtoOptional = studentDtoService.findByStudentIdAndCardId(Long.valueOf(studentId),
                Long.valueOf(cardId));

        model.addAttribute("card", cardDtoOptional.orElse(null));
        model.addAttribute("student", studentDtoOptional.orElse(null));
        return "students/cards/show";
    }

    @GetMapping("/students/{studentId}/locations")
    public String listStudentLocations(@PathVariable String studentId, Model model){
        log.debug("Getting location list for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Set<LocationDto> locationsDto = studentDtoService.findLocationsByStudentId(Long.valueOf(studentId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationsDto);
        model.addAttribute("student", studentDtoOptional.orElse(null));
        return "students/locations/list";
    }

    @GetMapping("/students/{studentId}/locations/{locationId}/show")
    public String showStudentLocation(@PathVariable String studentId, @PathVariable String locationId, Model model){
        log.debug("Getting location id " + locationId + " for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Optional<LocationDto> locationDtoOptional =
                studentDtoService.findByStudentIdAndLocationId(Long.valueOf(studentId), Long.valueOf(locationId));

        model.addAttribute("location", locationDtoOptional.orElse(null));
        model.addAttribute("student", studentDtoOptional.orElse(null));
        return "students/locations/show";
    }

    @GetMapping("/students/{studentId}/slots")
    public String listStudentSlots(@PathVariable String studentId, Model model){
        log.debug("Getting slots list for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Set<SlotDto> slotsDto = studentDtoService.findSlotsByStudentId(Long.valueOf(studentId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("student", studentDtoOptional.orElse(null));
        return "students/slots/list";
    }

    @GetMapping("/students/{studentId}/slots/{slotId}/show")
    public String showStudentSlot(@PathVariable String studentId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Optional<SlotDto> slotDtoOptional =
                studentDtoService.findByStudentIdAndSlotId(Long.valueOf(studentId), Long.valueOf(slotId));

        model.addAttribute("slot", slotDtoOptional.orElse(null));
        model.addAttribute("student", studentDtoOptional.orElse(null));
        return "students/slots/show";
    }
}
