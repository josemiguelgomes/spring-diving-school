package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class CourseController {

    private final CourseDtoService courseDtoService;

    public CourseController(CourseDtoService courseDtoService) {
        this.courseDtoService = courseDtoService;
    }

    @RequestMapping({"/courses", "/courses/index", "/courses/index.html", "courses.html"})
    public String listCourses(Model model){
        model.addAttribute("courses", courseDtoService.findAll());
        return "courses/index";
    }

    @RequestMapping({"/courses/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("course", courseDtoService.findById(Long.valueOf(id)));
        return "courses/show";
    }

    @GetMapping("courses/new")
    public String newCourse(Model model){
        model.addAttribute("course", new CourseDto());
        return "courses/courseform";
    }

    @GetMapping("courses/{id}/update")
    public String updateCourse(@PathVariable String id, Model model){
        model.addAttribute("course", courseDtoService.findById(Long.valueOf(id)));
        return  "courses/courseform";
    }

    @PostMapping("courses")
    public String saveOrUpdate(@ModelAttribute CourseDto courseDto){
        CourseDto savedCourseDto = courseDtoService.save(courseDto);

        return "redirect:/courses/" + savedCourseDto.getId() + "/show";
    }

    @GetMapping("courses/{id}/delete")
    public String deleteById(@PathVariable String id) {
        courseDtoService.deleteById(Long.valueOf(id));
        return "redirect:/courses";
    }

    /* --- */

    @GetMapping("/courses/{courseId}/slots")
    public String listSlotsCourse(@PathVariable String courseId, Model model){
        log.debug("Getting slots list for course id: " + courseId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("course", courseDtoService.findById(Long.valueOf(courseId)));
        return "courses/slots/list";
    }

    @GetMapping("/courses/{courseId}/slots/{slotId}/show")
    public String showSlotCourse(@PathVariable String courseId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for course id: " + courseId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("course", courseDtoService.findById(Long.valueOf(slotId)));
        return "courses/slots/show";
    }
}
