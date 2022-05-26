package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CourseConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.CourseExtendedService;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Slot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private static final String VIEWS_SLOTS_COURSES_LIST = "slots/courses/list";
    private static final String VIEWS_SLOTS_COURSES_SHOW = "slots/courses/show";
    private static final String VIEWS_SLOTS_COURSES_COURSEFORM = "slots/courses/courseform";

    private static final String REDIRECT_SLOTS = "redirect:/slots";
    private static final String REDIRECT_SLOTS_COURSES = "redirect:/slots/courses";
    private static final String REDIRECT_COURSES = "redirect:/courses";

    private final CourseExtendedService courseService;
    private final SlotExtendedService slotService;

    private final CourseConverter courseConverter;
    private final SlotConverter slotConverter;

    public CourseController(CourseExtendedService courseService,
                            SlotExtendedService slotService,
                            CourseConverter courseConverter,
                            SlotConverter slotConverter) {
        this.courseService = courseService;
        this.slotService = slotService;
        this.courseConverter = courseConverter;
        this.slotConverter = slotConverter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/courses/list", "/courses/index", "/courses/index.html", "courses.html"})
    public String listCourses(Model model){
        Set<Course> courses = courseService.findAll();
        Set<CourseDto> coursesDto = courseConverter.convertFromEntities(courses);
        model.addAttribute("courses", coursesDto);
        return VIEWS_COURSES_LIST;
    }

    @RequestMapping({"/courses/{id}/show"})
    public String showById(@PathVariable Long id, Model model){
        Optional<Course> courseOptional = courseService.findById(id);
        CourseDto courseDto = courseConverter.convertFromEntity(courseOptional.get());
        model.addAttribute("course", courseDto);
        return VIEWS_COURSES_SHOW;
    }

    @GetMapping("courses/new")
    public String newCourse(Model model){
        model.addAttribute("course", CourseDto.builder().build());
        return VIEWS_COURSES_COURSEFORM;
    }

    @GetMapping("courses/{id}/update")
    public String updateCourse(@PathVariable Long id, Model model){
        Optional<Course> courseOptional = courseService.findById(id);
        CourseDto courseDto = courseConverter.convertFromEntity(courseOptional.get());
        model.addAttribute("course", courseDto);
        return VIEWS_COURSES_COURSEFORM;
    }

    @PostMapping("courses")
    public String saveOrUpdate(@ModelAttribute CourseDto courseDto){
        Course course = courseConverter.convertFromDto(courseDto);
        Course savedCourse = courseService.save(course);
        CourseDto savedCourseDto = courseConverter.convertFromEntity(savedCourse);
        return REDIRECT_COURSES + "/" + savedCourseDto.getId() + "/show";
    }

    @GetMapping("courses/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return REDIRECT_COURSES;
    }

    /* --- */

    @GetMapping("/courses/find")
    public String findCourses(Model model) {
        Set<Course> courses = courseService.findAll();
        Set<CourseDto> coursesDto = courseConverter.convertFromEntities(courses);
        model.addAttribute("courses", coursesDto);
        return VIEWS_COURSES_FIND;
    }

    /* --- */

    @GetMapping("/slots/{slotId}/courses")
    public String listSlotCourses(@PathVariable Long slotId, Model model){
        log.debug("Getting courses list for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(slotId);
        Set<Course> courses = slotService.findCoursesBySlotId(slotId);

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        Set<CourseDto> coursesDto = courseConverter.convertFromEntities(courses);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("courses", coursesDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_COURSES_LIST;
    }

    @GetMapping("/slots/{slotId}/courses/new")
    public String initNewSlotCourse(@PathVariable Long slotId, Model model){
        log.debug("Getting slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(slotId);
        Course course = Course.builder().build();
        slotOptional.ifPresent(slot -> slot.setCourse(course));

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        CourseDto courseDto = courseConverter.convertFromEntity(course);

        model.addAttribute("slot", slotDto);
        model.addAttribute("course", courseDto);
        return VIEWS_SLOTS_COURSES_COURSEFORM;
    }

    @PostMapping("/slots/{slotId}/courses/new")
    public String processNewSlotCourse(@PathVariable Long slotId,
                                       SlotDto slotDto,
                                       @Valid CourseDto courseDto,
                                       BindingResult result,
                                       Model model) {
        log.debug("Getting slot id: " + slotId);

        if (result.hasErrors()) {
            model.addAttribute("course", courseDto);
            return VIEWS_SLOTS_COURSES_COURSEFORM;
        } else {
            Course course = courseConverter.convertFromDto(courseDto);
            Slot slot = slotService.findById(slotId).get();

            // Link the Entities
            slot.setCourse(course);
            course.getSlots().add(slot);

            Course courseSaved = courseService.save(course);

            return REDIRECT_SLOTS + "/" + courseSaved.getId() + "/show";
        }
    }

    @GetMapping("/slots/{slotId}/courses/{courseId}/delete")
    public String deleteSlotCourse(@PathVariable Long slotId, @PathVariable Long courseId, Model model){
        log.debug("Getting slot id: " + slotId + " and Course Id: " + courseId);

        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        slotService.deleteBySlotIdAndCourseId(slotId, courseId);

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDto);
        return REDIRECT_SLOTS_COURSES;
    }

    @GetMapping("/slots/{slotId}/courses/{courseId}/show")
    public String showSlotCourse(@PathVariable Long slotId, @PathVariable Long courseId, Model model){
        log.debug("Getting course id " + courseId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(slotId);
        Optional<Course> courseOptional =
                slotService.findBySlotIdAndCourseId(slotId, courseId);

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        CourseDto courseDto = courseConverter.convertFromEntity(courseOptional.get());

        model.addAttribute("course", courseDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_COURSES_SHOW;
    }
}
