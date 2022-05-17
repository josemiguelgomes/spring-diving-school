package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.CourseConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.CourseExtendedService;
import com.zem.diveschool.dto.*;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.model.Slot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class CourseController {

    private final CourseExtendedService service;
    private final CourseConverter converter;
    private final SlotConverter slotConverter;

    public CourseController(CourseExtendedService service,
                            CourseConverter converter,
                            SlotConverter slotConverter) {
        this.service = service;
        this.converter = converter;
        this.slotConverter = slotConverter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/courses", "/courses/index", "/courses/index.html", "courses.html"})
    public String listCourses(Model model){
        Set<Course> courses = service.findAll();
        Set<CourseDto> coursesDto = converter.convertFromEntities(courses);
        model.addAttribute("courses", coursesDto);
        return "courses/index";
    }

    @RequestMapping({"/courses/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Optional<Course> courseOptional = service.findById(Long.valueOf(id));
        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        model.addAttribute("course", courseDto);
        return "courses/show";
    }

    @GetMapping("courses/new")
    public String newCourse(Model model){
        model.addAttribute("course", CourseDto.builder().build());
        return "courses/courseform";
    }

    @GetMapping("courses/{id}/update")
    public String updateCourse(@PathVariable String id, Model model){
        Optional<Course> courseOptional = service.findById(Long.valueOf(id));
        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());
        model.addAttribute("course", courseDto);
        return  "courses/courseform";
    }

    @PostMapping("courses")
    public String saveOrUpdate(@ModelAttribute CourseDto courseDto){
        Course course = converter.convertFromDto(courseDto);
        Course savedCourse = service.save(course);
        CourseDto savedCourseDto = converter.convertFromEntity(savedCourse);
        return "redirect:/courses/" + savedCourseDto.getId() + "/show";
    }

    @GetMapping("courses/{id}/delete")
    public String deleteById(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        return "redirect:/courses";
    }

    /* --- */

    @GetMapping("/courses/find")
    public String findCourses(Model model) {
        Set<Course> courses = service.findAll();
        Set<CourseDto> coursesDto = converter.convertFromEntities(courses);
        model.addAttribute("courses", coursesDto);
        return "courses/find";
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
        return "courses/slots/list";
    }

    @GetMapping("/courses/{courseId}/slots/new")
    public String newCourseSlot(@PathVariable String courseId, Model model) {
        log.debug("Getting course id " + courseId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Course> courseOptional = service.findById(Long.valueOf(courseId));
        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());

        // need to return back parent id for hidden form property
        SlotDto slotDto = new SlotDto();
        slotDto.setCourse(courseDto);

        model.addAttribute("slot", slotDto);
        return "courses/slots/slotform";
    }

    @GetMapping("/courses/{courseId}/slots/{slotId}/delete")
    public String deleteCourseSlot(@PathVariable String courseId, @PathVariable String slotId, Model model) {
        log.debug("Getting course id " + courseId);

        Optional<Course> courseOptional = service.findById(Long.valueOf(courseId));
        service.deleteByCourseIdAndSlotId(Long.valueOf(courseId), Long.valueOf(slotId));

        CourseDto courseDto = converter.convertFromEntity(courseOptional.get());

        model.addAttribute("course", courseDto);
        return "redirect:/courses/slots";
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
        return "courses/slots/show";
    }
}
