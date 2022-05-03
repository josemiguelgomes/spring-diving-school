package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class CourseController {

    private final CourseDtoService courseDtoService;

    @Autowired
    private ConvertObjectToObject<Course, CourseDto> convertToDto;
    @Autowired
    private ConvertObjectToObject<CourseDto, Course> convertToEntity;


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
        return  "coursess/courseform";
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

    @GetMapping("/api/courses")
    public @ResponseBody Set<CourseDto> getCourseJson(){
        return courseDtoService.findAll();
    }
}
