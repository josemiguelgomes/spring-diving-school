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

    @GetMapping("/students/{studentId}/cards/new")
    public String newStudentCard(@PathVariable String studentId, Model model) {
        log.debug("Getting student id " + studentId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));

        // need to return back parent id for hidden form property
        CardDto cardDto = new CardDto();
        cardDto.setStudent(studentDtoOptional.get());

        model.addAttribute("card", cardDto);
        return "students/cards/cardform";
    }

    @GetMapping("/students/{studentId}/cards/{cardId}/delete")
    public String deleteStudentCard(@PathVariable String studentId, @PathVariable String cardId, Model model) {
        log.debug("Getting student id " + studentId + " and card id " + cardId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        studentDtoService.deleteByStudentIdAndCardId(Long.valueOf(studentId), Long.valueOf(cardId));

        model.addAttribute("student", studentDtoOptional.get());
        return "redirect:/students/cards";
    }

    @GetMapping("/students/{studentId}/cards/{cardId}/show")
    public String showStudentCard(@PathVariable String studentId, @PathVariable String cardId, Model model){
        log.debug("Getting card id " + cardId + " for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Optional<CardDto> cardDtoOptional = studentDtoService.findByStudentIdAndCardId(Long.valueOf(studentId),
                Long.valueOf(cardId));

        model.addAttribute("card", cardDtoOptional.get());
        model.addAttribute("student", studentDtoOptional.get());
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

    @GetMapping("/students/{studentId}/locations/new")
    public String newStudentLocation(@PathVariable String studentId, Model model) {
        log.debug("Getting student id " + studentId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));

        // need to return back parent id for hidden form property
        LocationDto locationDto = new LocationDto();
//      locationDto.setStudent(studentDtoOptional.get());
        model.addAttribute("location", locationDto);

        return "students/locations/locationform";
    }

    @GetMapping("/students/{studentId}/locations/{locationId}/delete")
    public String deleteStudentLocation(@PathVariable String studentId, @PathVariable String locationId, Model model) {
        log.debug("Getting student id " + studentId + " and location id " + locationId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        studentDtoService.deleteByStudentIdAndLocationId(Long.valueOf(studentId), Long.valueOf(locationId));

        model.addAttribute("student", studentDtoOptional.get());
        return "redirect:/students/locations";
    }

    @GetMapping("/students/{studentId}/locations/{locationId}/show")
    public String showStudentLocation(@PathVariable String studentId, @PathVariable String locationId, Model model){
        log.debug("Getting location id " + locationId + " for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Optional<LocationDto> locationDtoOptional =
                studentDtoService.findByStudentIdAndLocationId(Long.valueOf(studentId), Long.valueOf(locationId));

        model.addAttribute("location", locationDtoOptional.get());
        model.addAttribute("student", studentDtoOptional.get());
        return "students/locations/show";
    }

    @GetMapping("/students/{studentId}/slots")
    public String listStudentSlots(@PathVariable String studentId, Model model){
        log.debug("Getting slots list for student id: " + studentId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        Set<SlotDto> slotsDto = studentDtoService.findSlotsByStudentId(Long.valueOf(studentId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("student", studentDtoOptional.get());
        return "students/slots/list";
    }

    @GetMapping("/students/{studentId}/slots/new")
    public String newStudentSlot(@PathVariable String studentId, Model model) {
        log.debug("Getting student id " + studentId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));

        // need to return back parent id for hidden form property
        SlotDto slotDto = new SlotDto();
        slotDto.getStudents().add(studentDtoOptional.get());
        model.addAttribute("slot", slotDto);
        return "students/slots/slotform";
    }

    @GetMapping("/students/{studentId}/slots/{slotId}/delete")
    public String deleteStudentSlot(@PathVariable String studentId, @PathVariable String slotId, Model model) {
        log.debug("Getting student id " + studentId + " and slot id " + slotId);

        Optional<StudentDto> studentDtoOptional = studentDtoService.findById(Long.valueOf(studentId));
        studentDtoService.deleteByStudentIdAndSlotId(Long.valueOf(studentId), Long.valueOf(slotId));

        model.addAttribute("student", studentDtoOptional.get());
        return "redirect:/students/slots";
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
