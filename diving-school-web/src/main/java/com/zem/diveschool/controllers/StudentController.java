package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CardConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.converters.impl.simple.StudentConverter;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.data.StudentExtendedService;
import com.zem.diveschool.dto.CardDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class StudentController {

    private static final String VIEWS_STUDENTS_LIST = "students/list";
    private static final String VIEWS_STUDENTS_SHOW = "students/show";
    private static final String VIEWS_STUDENTS_STUDENTFORM = "students/studentform";
    private static final String VIEWS_STUDENTS_IMAGEUPLOADFORM = "students/imageuploadform";
    private static final String VIEWS_STUDENTS_FIND = "students/find";

    private static final String VIEWS_SLOTS_STUDENTS_LIST = "slots/students/list";
    private static final String VIEWS_SLOTS_STUDENTS_STUDENTFORM = "slots/student/studentform";
    private static final String VIEWS_SLOTS_STUDENTS_SHOW = "slots/students/show";

    private static final String REDIRECT_STUDENTS = "redirect:/students/list";
    private static final String REDIRECT_SLOTS_STUDENTS = "redirect:/slots/students";

    private final StudentExtendedService studentService;
    private final SlotExtendedService slotService;

    private final StudentConverter studentConverter;
    private final SlotConverter slotConverter;
    private final LocationConverter locationConverter;
    private final CardConverter cardConverter;

    public StudentController(StudentExtendedService studentService,
                             SlotExtendedService slotService,
                             StudentConverter studentConverter,
                             SlotConverter slotConverter,
                             LocationConverter locationConverter,
                             CardConverter cardConverter) {
        this.studentService = studentService;
        this.slotService = slotService;
        this.studentConverter = studentConverter;
        this.slotConverter = slotConverter;
        this.locationConverter = locationConverter;
        this.cardConverter = cardConverter;
    }

//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    @GetMapping({"students", "/students/list", "/students/index", "/students/index.html"})
    public String listStudents(@NotNull Model model){
        Set<Student> students = studentService.findAll();
        Set<StudentDto> studentsDto = studentConverter.convertFromEntities(students);
        model.addAttribute("students", studentsDto);
        return VIEWS_STUDENTS_LIST;
    }

    @GetMapping({"/students/{id}/show"})
    public String showById(@PathVariable String id, @NotNull Model model){
        Optional<Student> studentOptional = studentService.findById(Long.valueOf(id));
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());

        studentDto.setHomeAddress(locationConverter.convertFromEntity(studentOptional.get().getHomeAddress()));
        studentDto.setCards(cardConverter.convertFromEntities(studentOptional.get().getCards()));
        studentDto.setSlots(slotConverter.convertFromEntities(studentOptional.get().getSlots()));

        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_SHOW;
    }

    @GetMapping("students/new")
    public String newStudent(@NotNull Model model){
        model.addAttribute("student", StudentDto.builder().build());
        return VIEWS_STUDENTS_STUDENTFORM;
    }

    @GetMapping("students/{id}/update")
    public String updateStudent(@PathVariable String id, @NotNull Model model){
        Optional<Student> studentOptional = studentService.findById(Long.valueOf(id));
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());

        studentDto.setHomeAddress(locationConverter.convertFromEntity(studentOptional.get().getHomeAddress()));

        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_STUDENTFORM;
    }

    @PostMapping("students")
    public String saveOrUpdate(@ModelAttribute StudentDto studentDto, BindingResult result){
        if (result.hasErrors()) {
           return VIEWS_STUDENTS_STUDENTFORM;
        } else {
           Student student = studentConverter.convertFromDto(studentDto);
           student.setHomeAddress(locationConverter.convertFromDto(studentDto.getHomeAddress()));

           Student savedStudent = studentService.save(student);

           StudentDto savedStudentDto = studentConverter.convertFromEntity(savedStudent);
           savedStudentDto.setHomeAddress(locationConverter.convertFromEntity(savedStudent.getHomeAddress()));
           return REDIRECT_STUDENTS + "/" + savedStudentDto.getId() + "/show";
        }
    }

    @GetMapping("students/{id}/delete")
    public String deleteById(@PathVariable String id){
        studentService.deleteById(Long.valueOf(id));
        return REDIRECT_STUDENTS;
    }

    /* --- */

    @GetMapping("/students/{id}/photo")
    public String showUploadForm(@PathVariable String id, @NotNull Model model){
        Optional<Student> studentOptional = studentService.findById(Long.valueOf(id));
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());
        model.addAttribute("student", studentDto);
        return VIEWS_STUDENTS_IMAGEUPLOADFORM;
    }

    @PostMapping("/students/{id}/photo")
    public String handleImagePost(@PathVariable String id,
                                  @RequestParam("imagefile") MultipartFile file){
        studentService.saveImageFile(Long.valueOf(id), file);
        return REDIRECT_STUDENTS + "/" + id + "/show";
    }

    /* --- */

    @GetMapping("/students/find")
    public String findStudents(Model model) {
        model.addAttribute("student", StudentDto.builder().build());
        return VIEWS_STUDENTS_FIND;
    }

    @PostMapping("/students/find")
    public String processFindForm(StudentDto studentDto, BindingResult result, Model model) {
        // allow parameterless GET request for /students to return all records
        if (studentDto.getLastName() == null) {
            studentDto.setLastName(""); // empty string signifies a more abroad possible search
        }

        // find students by last name
        Student student = studentConverter.convertFromDto(studentDto);
        Set<Student> students = studentService.findAllByLastNameLike("%" + student.getLastName() + "%");
        Set<StudentDto> studentsDto = studentConverter.convertFromEntities(students);

        if (studentsDto.isEmpty()) {
            // no students found
            result.rejectValue("lastName", "notFound", "not found");
            return VIEWS_STUDENTS_FIND;
        } else if (studentsDto.size() == 1) {
            // 1 student found
            studentDto = studentsDto.stream().findFirst().get();
            return REDIRECT_STUDENTS + "/" + studentDto.getId() + "/show";
        } else {
            // multiple students found
            model.addAttribute("students", studentsDto);
            return VIEWS_STUDENTS_LIST;
        }
    }

    /* --- */

    @GetMapping("/slots/{slotId}/students")
    public String listSlotStudents(@PathVariable String slotId, Model model){
        log.debug("Getting students list for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        Set<Student> students = slotService.findStudentsBySlotId(Long.valueOf(slotId));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        Set<StudentDto> studentsDto = studentConverter.convertFromEntities(students);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("students", studentsDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_STUDENTS_LIST;
    }

    @GetMapping("/slots/{slotId}/students/{studentId}/new")
    public String initNewSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model) {
        log.debug("Getting slot id " + slotId);

        // Find Slot and convert into DTO
        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        slotDto.setStudents(studentConverter.convertFromEntities(slotOptional.get().getStudents()));

        // Find Student and convert into DTO
        Optional<Student> studentOptional = studentService.findById(Long.valueOf(studentId));
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());
        studentDto.setSlots(slotConverter.convertFromEntities(studentOptional.get().getSlots()));

        // Link the two DTOs
        slotDto.getStudents().add(studentDto);
        studentDto.getSlots().add(slotDto);

        model.addAttribute("slot", slotDto);
        model.addAttribute("student", studentDto);
        return VIEWS_SLOTS_STUDENTS_STUDENTFORM;
    }

    @PostMapping("/slots/{slotId}/students/{studentID}/new")
    public String processNewSlotStudent(@PathVariable Long slotId,
                                        @PathVariable Long studentId,
                                        BindingResult result, ModelMap model) {

        // Find Slot and convert into DTO
        Optional<Slot> slotOptional = slotService.findById(slotId);
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        slotDto.setStudents(studentConverter.convertFromEntities(slotOptional.get().getStudents()));

        // Find Student and convert into DTO
        Optional<Student> studentOptional = studentService.findById(studentId);
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());
        studentDto.setSlots(slotConverter.convertFromEntities(studentOptional.get().getSlots()));

        if (!studentDto.getSlots().isEmpty()
            && studentDto.getSlots().stream().anyMatch(slot -> slot.getStudents().contains(studentDto))) {
            result.rejectValue("student", "duplicate", "already exists");
        }

        // Link the two DTOs
        slotDto.getStudents().add(studentDto);
        studentDto.getSlots().add(slotDto);

        if (result.hasErrors()) {
            model.put("slot", slotDto);
            model.put("student", studentDto);
            return VIEWS_SLOTS_STUDENTS_STUDENTFORM;
        } else {
            Slot slot = slotConverter.convertFromDto(slotDto);
            slot.setStudents(studentConverter.convertFromDtos(slotDto.getStudents()));

            Student student = studentConverter.convertFromDto(studentDto);
            student.setSlots(slotConverter.convertFromDtos(studentDto.getSlots()));

            studentService.save(student);

            return REDIRECT_SLOTS_STUDENTS + "/" + slotDto.getId() + "/show";
        }
    }

    @GetMapping("/slots/{slotId}/student/{studentId}/delete")
    public String deleteSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting slot id: " + slotId + " and student Id: " + studentId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        slotService.deleteBySlotIdAndStudentId(Long.valueOf(slotId), Long.valueOf(studentId));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("student", slotDto);
        return REDIRECT_SLOTS_STUDENTS;
    }

    @GetMapping("/slots/{slotId}/students/{studentId}/show")
    public String showSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting student id " + studentId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        Optional<Student> studentOptional =
                slotService.findBySlotIdAndStudentId(Long.valueOf(slotId), Long.valueOf(studentId));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());

        model.addAttribute("student", studentDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_STUDENTS_SHOW;
    }
}
