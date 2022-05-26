package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CourseConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.CourseExtendedService;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.model.Card;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class CourseController {

    private static final String VIEWS_COURSES_LIST = "courses/list";
    private static final String VIEWS_COURSES_SHOW = "courses/show";
    private static final String VIEWS_COURSES_COURSEFORM = "courses/courseform";
    private static final String VIEWS_COURSES_FIND = "courses/find";

    private static final String VIEWS_COURSES_SLOTS_LIST = "courses/slots/list";
    private static final String VIEWS_COURSES_SLOTS_SLOTFORM = "courses/slots/slotform";
    private static final String VIEWS_COURSES_SLOTS_SHOW = "courses/slots/show";

    private static final String VIEWS_SLOTS_COURSES_COURSEFORM = "slots/courses/courseform";

    private static final String REDIRECT_COURSES = "redirect:/courses";
    private static final String REDIRECT_COURSES_SLOTS = "redirect:/courses/slots";

    private final CourseExtendedService service;
    private final SlotExtendedService slotService;

    private final CourseConverter converter;
    private final SlotConverter slotConverter;

    public CourseController(CourseExtendedService service,
                            SlotExtendedService slotService,
                            CourseConverter converter,
                            SlotConverter slotConverter) {
        this.service = service;
        this.slotService = slotService;
        this.converter = converter;
        this.slotConverter = slotConverter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/courses/list", "/courses/index", "/courses/index.html", "courses.html"})
    public String listCourses(Model model){
        Set<Course> courses = service.findAll();
        Set<CourseDto> coursesDto = converter.convertFromEntities(courses);
        model.addAttribute("courses", coursesDto);
        return VIEWS_COURSES_LIST;
    }

    @RequestMapping({"/courses/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Optional<Course> courseOptional = service.findById(Long.valueOf(id));
        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        model.addAttribute("course", courseDto);
        return VIEWS_COURSES_SHOW;
    }

    @GetMapping("courses/new")
    public String newCourse(Model model){
        model.addAttribute("course", CourseDto.builder().build());
        return VIEWS_COURSES_COURSEFORM;
    }

    @GetMapping("courses/{id}/update")
    public String updateCourse(@PathVariable String id, Model model){
        Optional<Course> courseOptional = service.findById(Long.valueOf(id));
        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        model.addAttribute("course", courseDto);
        return VIEWS_COURSES_COURSEFORM;
    }

    @PostMapping("courses")
    public String saveOrUpdate(@ModelAttribute CourseDto courseDto){
        Course course = converter.convertFromDto(courseDto);
        Course savedCourse = service.save(course);
        CourseDto savedCourseDto = converter.convertFromEntity(savedCourse);
        return REDIRECT_COURSES + "/" + savedCourseDto.getId() + "/show";
    }

    @GetMapping("courses/{id}/delete")
    public String deleteById(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        return REDIRECT_COURSES;
    }

    /* --- */

    @GetMapping("/courses/find")
    public String findCourses(Model model) {
        Set<Course> courses = service.findAll();
        Set<CourseDto> coursesDto = converter.convertFromEntities(courses);
        model.addAttribute("courses", coursesDto);
        return VIEWS_COURSES_FIND;
    }

    /* --- */

    @GetMapping("/courses/{courseId}/slots")
    public String listCourseSlots(@PathVariable String courseId, Model model){
        log.debug("Getting slots list for course id: " + courseId);

        Optional<Course> courseOptional = service.findById(Long.valueOf(courseId));
        Set<Slot> slots = service.findSlotsByCourseId(Long.valueOf(courseId));

        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        Set<SlotDto> slotsDto = slotConverter.convertFromEntities(slots);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("course", courseDto);
        return VIEWS_COURSES_SLOTS_LIST;
    }

    @GetMapping("/slots/{slotId}/courses/{courseId}/new")
    public String initNewStudentCard(@PathVariable String slotId, @PathVariable String courseId, Model model) {
        log.debug("Getting slot id " + slotId + " course id " + courseId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        slotDto.setCourse(converter.convertFromEntity(slotOptional.get().getCourse()));

        Optional<Course> courseOptional = service.findById(Long.valueOf(courseId));
        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        courseDto.setSlots(slotConverter.convertFromEntities(courseOptional.get().getSlots()));

        // Link the two DTOs
        slotDto.setCourse(courseDto);
        courseDto.getSlots().add(slotDto);

        model.addAttribute("slot", slotDto);
        model.addAttribute("course", courseDto);
        return VIEWS_SLOTS_COURSES_COURSEFORM;
    }

    /*   TODO
    @PostMapping("/slots/{studentId}/courses/{courseId}/new")
    public String processNewStudentCard(@PathVariable String studentId,
                                        @Valid Course cardDto,
                                        BindingResult result, ModelMap model) {
        Optional<Student> studentOptional = studentService.findById(Long.valueOf(studentId));
        StudentDto studentDto = studentConverter.convertFromEntity(studentOptional.get());
        studentDto.setCards(converter.convertFromEntities(studentOptional.get().getCards()));

        if (StringUtils.hasLength(cardDto.getCourse())
                && cardDto.isNew()
                && studentDto.getCards().stream().anyMatch(card -> card.getCourse().equals(cardDto.getCourse()))) {
            result.rejectValue("course", "duplicate", "already exists");
        }
        // Link the two DTOs
        cardDto.setStudent(studentDto);
        studentDto.getCards().add(cardDto);
        if (result.hasErrors()) {
            model.put("student", studentDto);
            model.put("card", cardDto);
            return VIEWS_STUDENTS_CARDS_CARDFORM;
        } else {
            Card card = converter.convertFromDto(cardDto);
            Student student = studentConverter.convertFromDto(cardDto.getStudent());
            card.setStudent(student);
            service.save(card);
            return REDIRECT_STUDENTS + "/" + studentDto.getId() + "/show";
        }
    }
     */

    @GetMapping("/courses/{courseId}/slots/{slotId}/delete")
    public String deleteCourseSlot(@PathVariable String courseId, @PathVariable String slotId, Model model) {
        log.debug("Getting course id " + courseId);

        Optional<Course> courseOptional = service.findById(Long.valueOf(courseId));
        service.deleteByCourseIdAndSlotId(Long.valueOf(courseId), Long.valueOf(slotId));

        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());

        model.addAttribute("course", courseDto);
        return REDIRECT_COURSES_SLOTS;
    }

    @GetMapping("/courses/{courseId}/slots/{slotId}/show")
    public String showCourseSlot(@PathVariable String courseId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for course id: " + courseId);

        Optional<Course> courseOptional = service.findById(Long.valueOf(courseId));
        Optional<Slot> slotOptional =
                service.findByCourseIdAndSlotId(Long.valueOf(courseId), Long.valueOf(slotId));

        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        model.addAttribute("slot", slotDto);
        model.addAttribute("course", courseDto);
        return VIEWS_COURSES_SLOTS_SHOW;
    }
}
