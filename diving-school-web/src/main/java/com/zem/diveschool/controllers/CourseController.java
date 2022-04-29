package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.converters.impl.CourseDtoToCourse;
import com.zem.diveschool.converters.impl.CourseToCourseDto;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.persistence.model.Course;
import com.zem.diveschool.persistence.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final ConvertObjectToObject<Course, CourseDto> convertToDto;
    private final ConvertObjectToObject<CourseDto, Course> convertToEntity;


    public CourseController(CourseService courseService,
                            ConvertObjectToObject<Course, CourseDto> convertToDto,
                            ConvertObjectToObject<CourseDto, Course> convertToEntity) {
        this.courseService = courseService;
        this.convertToDto = convertToDto;
        this.convertToEntity = convertToEntity;
    }

    @RequestMapping({"/courses", "/courses/index", "/courses/index.html", "courses.html"})
    public String listCourses(Model model){
        model.addAttribute("courses", convertToDto.convert(courseService.findAll()));
        return "courses/index";
    }

    @RequestMapping({"/courses/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Course course = courseService.findById(Long.valueOf(id));
        model.addAttribute("course", course);

        return "courses/show";
    }

    @GetMapping("courses/new")
    public String newCourse(Model model){
        model.addAttribute("course", new CourseDto());

        return "courses/courseform";
    }

    @GetMapping("courses/{id}/update")
    public String updateCourse(@PathVariable String id, Model model){
//        model.addAttribute("course", courseService.findCommandById(Long.valueOf(id)));// TODO: create this on services
        model.addAttribute("course", new CourseToCourseDto().convert(courseService.findById(Long.valueOf(id))));
        return  "courses/courseform";
    }

    @PostMapping("courses")
    public String saveOrUpdate(@ModelAttribute CourseDto courseDto){
        Course savedCourse = courseService.save(new CourseDtoToCourse().convert(courseDto));

        return "redirect:/courses/" + savedCourse.getId() + "/show";
    }

    @GetMapping("courses/{id}/delete")
    public String deleteById(@PathVariable String id){
         // Delete the course
        courseService.deleteById(Long.valueOf(id));
        return "redirect:/courses";
    }

    @GetMapping("/api/courses")
    public @ResponseBody Set<Course> getCourseJson(){
        return courseService.findAll();
    }

}
