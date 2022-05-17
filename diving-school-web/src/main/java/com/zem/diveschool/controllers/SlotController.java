package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.*;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.*;

import com.zem.diveschool.persistence.model.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class SlotController {

    private static final String VIEWS_SLOTS_INDEX = "slots/index";
    private static final String VIEWS_SLOTS_SHOW = "slots/show";
    private static final String VIEWS_SLOTS_SLOTFORM = "slots/slotform";
    private static final String VIEWS_SLOTS_FIND = "slots/find";

    private static final String VIEWS_SLOTS_COURSES_LIST = "slots/courses/list";
    private static final String VIEWS_SLOTS_SOURSES_COURSEFORM = "slots/courses/courseform";
    private static final String VIEWS_SLOTS_COURSES_SHOW = "slots/courses/show";

    private static final String VIEWS_SLOTS_INSTRUCTORS_LIST = "slots/instructors/list";
    private static final String VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM = "slots/instructors/instructorform";
    private static final String VIEWS_SLOTS_INSTRUCTORS_SHOW = "slots/instructors/show";

    private static final String VIEWS_SLOTS_SLOTLANGUAGES_LIST = "slots/slotLanguages/list";
    private static final String VIEWS_SLOTS_SLOTLANGUAGES_SLOTLANGUAGEFORM = "slots/slotLanguages/slotLanguageform";
    private static final String VIEWS_SLOTS_SLOTLANGUAGES_SHOW = "slots/slotLanguages/show";

    private static final String VIEWS_SLOTS_STUDENTS_LIST = "slots/students/list";
    private static final String VIEWS_SLOTS_STUDENTS_STUDENTFORM = "slots/student/studentform";
    private static final String VIEWS_SLOTS_STUDENTS_SHOW = "slots/students/show";

    private static final String REDIRECT_SLOTS = "redirect:/slots";
    private static final String REDIRECT_SLOTS_COURSES = "redirect:/slots/courses";
    private static final String REDIRECT_SLOTS_INSTRUCTORS = "redirect:/slots/instructors";
    private static final String REDIRECT_SLOTS_SLOTLANGUAGES = "redirect:/slots/slotLanguages";
    private static final String REDIRECT_SLOTS_STUDENTS = "redirect:/slots/students";

    private final SlotExtendedService service;
    private final SlotConverter converter;
    private final CourseConverter courseConverter;
    private final InstructorConverter instructorConverter;
    private final SlotLanguageConverter slotLanguageConverter;
    private final StudentConverter studentConverter;

    public SlotController(SlotExtendedService service,
                          SlotConverter converter,
                          CourseConverter courseConverter,
                          InstructorConverter instructorConverter,
                          SlotLanguageConverter slotLanguageConverter,
                          StudentConverter studentConverter) {
        this.service = service;
        this.converter = converter;
        this.courseConverter = courseConverter;
        this.instructorConverter = instructorConverter;
        this.slotLanguageConverter = slotLanguageConverter;
        this.studentConverter = studentConverter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/slots", "/slots/index", "/slots/index.html", "slots.html"})
    public String listSlots(@NotNull Model model){
        Set<Slot> slots = service.findAll();
        Set<SlotDto> slotsDto = converter.convertFromEntities(slots);
        model.addAttribute("slots", slotsDto);
        return VIEWS_SLOTS_INDEX;
    }

    @GetMapping({"/slots/{id}/show"})
    public String showById(@PathVariable String id, @NotNull Model model) {
        Optional<Slot> slotOptional = service.findById(Long.valueOf(id));
        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SHOW;
    }

    @GetMapping("slots/new")
    public String newSlot(@NotNull Model model){
        model.addAttribute("slot", SlotDto.builder().build());
        return VIEWS_SLOTS_SLOTFORM;
    }

    @GetMapping("slots/{id}/update")
    public String updateSlot(@PathVariable String id, @NotNull Model model){
        Optional<Slot> slot = service.findById(Long.valueOf(id));
        SlotDto slotDto = converter.convertFromEntity(slot.get());
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SLOTFORM;
    }

    @PostMapping("slots")
    public String saveOrUpdate(@ModelAttribute SlotDto slotDto){
        Slot slot = converter.convertFromDto(slotDto);
        Slot savedSlot = service.save(slot);
        SlotDto savedSlotDto = converter.convertFromEntity(savedSlot);
        return REDIRECT_SLOTS + "/" + savedSlotDto.getId() + "/show";
    }

    @GetMapping("slots/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return "redirect:/slots";
    }

    /* --- */

    @RequestMapping("/slots/find")
    public String findSlots(Model model) {
        Set<Slot> slots = service.findAll();
        Set<SlotDto> slotsDto = converter.convertFromEntities(slots);
        model.addAttribute("slots", slotsDto);
        return VIEWS_SLOTS_FIND;
    }

    /* --- */

    @GetMapping("/slots/{slotId}/courses")
    public String listSlotCourses(@PathVariable String slotId, Model model){
        log.debug("Getting courses list for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Set<Course> courses = service.findCoursesBySlotId(Long.valueOf(slotId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        Set<CourseDto> coursesDto = courseConverter.convertFromEntities(courses);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("courses", coursesDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_COURSES_LIST;
    }

    @GetMapping("/slots/{slotId}/courses/new")
    public String newSlotCourses(@PathVariable String slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Course course = new Course();
        slotOptional.ifPresent(slot -> slot.setCourse(course));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        CourseDto courseDto = courseConverter.convertFromEntity(course);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("course", courseDto);
        return VIEWS_SLOTS_SOURSES_COURSEFORM;
    }

    @GetMapping("/slots/{slotId}/courses/{courseId}/delete")
    public String deleteSlotCourse(@PathVariable String slotId, @PathVariable String courseId, Model model){
        log.debug("Getting slot id: " + slotId + " and Course Id: " + courseId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        service.deleteBySlotIdAndCourseId(Long.valueOf(slotId), Long.valueOf(courseId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDto);
        return REDIRECT_SLOTS_COURSES;
    }

    @GetMapping("/slots/{slotId}/courses/{courseId}/show")
    public String showSlotCourse(@PathVariable String slotId, @PathVariable String courseId, Model model){
        log.debug("Getting course id " + courseId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Optional<Course> courseOptional =
                service.findBySlotIdAndCourseId(Long.valueOf(slotId), Long.valueOf(courseId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        CourseDto courseDto = courseConverter.convertFromEntity(courseOptional.get());

        model.addAttribute("course", courseDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_COURSES_SHOW;
    }

    @GetMapping("/slots/{slotId}/instructors")
    public String listSlotInstructors(@PathVariable String slotId, Model model){
        log.debug("Getting instructors list for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Set<Instructor> instructors = service.findInstructorsBySlotId(Long.valueOf(slotId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        Set<InstructorDto> instructorsDto = instructorConverter.convertFromEntities(instructors);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructors", instructorsDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_INSTRUCTORS_LIST;
    }

    @GetMapping("/slots/{slotId}/instructors/new")
    public String newSlotInstructor(@PathVariable String slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Instructor instructor = new Instructor();
        slotOptional.ifPresent(slot -> slot.getInstructors().add(instructor));

        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructor);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructor", instructorDto);
        return VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM;
    }

    @GetMapping("/slots/{slotId}/instructors/{instructorId}/delete")
    public String deleteSlotInstructor(@PathVariable String slotId, @PathVariable String instructorId, Model model){
        log.debug("Getting slot id: " + slotId + " and instructor Id: " + instructorId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        service.deleteBySlotIdAndInstructorId(Long.valueOf(slotId), Long.valueOf(instructorId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDto);
        return REDIRECT_SLOTS_INSTRUCTORS;
    }

    @GetMapping("/slots/{slotId}/instructors/{instructorId}/show")
    public String showSlotInstructor(@PathVariable String slotId, @PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Optional<Instructor> instructorOptional =
                service.findBySlotIdAndInstructorId(Long.valueOf(slotId), Long.valueOf(instructorId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());

        model.addAttribute("instructor", instructorDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_INSTRUCTORS_SHOW;
    }

    @GetMapping("/slots/{slotId}/slotlanguages")
    public String listSlotsSlotLanguages(@PathVariable String slotId, Model model){
        log.debug("Getting slot languages list for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Set<SlotLanguage> slotLanguages = service.findLanguagesBySlotId(Long.valueOf(slotId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        Set<SlotLanguageDto> slotLanguagesDto = slotLanguageConverter.convertFromEntities(slotLanguages);


        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotLanguages", slotLanguagesDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SLOTLANGUAGES_LIST;
    }

    @GetMapping("/slots/{slotId}/slotLanguages/new")
    public String newSlotSlotLanguage(@PathVariable String slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        SlotLanguage slotLanguage = new SlotLanguage();
        slotOptional.ifPresent(slot -> slot.getLanguages().add(slotLanguage));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        SlotLanguageDto slotLanguageDto = slotLanguageConverter.convertFromEntity(slotLanguage);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slotLanguage", slotLanguageDto);
        return VIEWS_SLOTS_SLOTLANGUAGES_SLOTLANGUAGEFORM;
    }

    @GetMapping("/slots/{slotId}/slotlanguages/{slotLanguageId}/delete")
    public String deleteSlotSlotLanguage(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot id: " + slotId + " and Language Id: " + slotLanguageId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        service.deleteBySlotIdAndSlotLanguageId(Long.valueOf(slotId), Long.valueOf(slotLanguageId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDto);
        return REDIRECT_SLOTS_SLOTLANGUAGES;
    }

    @GetMapping("/slots/{slotId}/slotlanguages/{slotLanguageId}/show")
    public String showSlotSlotLanguage(@PathVariable String slotId, @PathVariable String slotLanguageId, Model model){
        log.debug("Getting slot language id " + slotLanguageId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Optional<SlotLanguage> slotLanguageOptional =
                service.findBySlotIdAndSlotLanguageId(Long.valueOf(slotId), Long.valueOf(slotLanguageId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        SlotLanguageDto slotLanguageDto = slotLanguageConverter.convertFromEntity(slotLanguageOptional.get());

        model.addAttribute("slotLanguage", slotLanguageDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_SLOTLANGUAGES_SHOW;
    }

    @GetMapping("/slots/{slotId}/students")
    public String listSlotStudents(@PathVariable String slotId, Model model){
        log.debug("Getting students list for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Set<Student> students = service.findStudentsBySlotId(Long.valueOf(slotId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        Set<StudentDto> studentsDto = studentConverter.convertFromEntities(students);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("students", studentsDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_STUDENTS_LIST;
    }

    @GetMapping("/slots/{slotId}/student/new")
    public String newSlotStudent(@PathVariable String slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Student student = new Student();
        slotOptional.ifPresent(slot -> slot.getStudents().add(student));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        StudentDto studentDto = studentConverter.convertFromEntity(student);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("student", studentDto);
        return VIEWS_SLOTS_STUDENTS_STUDENTFORM;
    }

    @GetMapping("/slots/{slotId}/student/{studentId}/delete")
    public String deleteSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting slot id: " + slotId + " and student Id: " + studentId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        service.deleteBySlotIdAndStudentId(Long.valueOf(slotId), Long.valueOf(studentId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("student", slotDto);
        return REDIRECT_SLOTS_STUDENTS;
    }

    @GetMapping("/slots/{slotId}/students/{studentId}/show")
    public String showSlotStudent(@PathVariable String slotId, @PathVariable String studentId, Model model){
        log.debug("Getting student id " + studentId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = service.findById(Long.valueOf(slotId));
        Optional<Student> studentOptional =
                service.findBySlotIdAndStudentId(Long.valueOf(slotId), Long.valueOf(studentId));

        SlotDto slotDto = converter.convertFromEntity(slotOptional.get());
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());

        model.addAttribute("student", studentDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_STUDENTS_SHOW;
    }
}
