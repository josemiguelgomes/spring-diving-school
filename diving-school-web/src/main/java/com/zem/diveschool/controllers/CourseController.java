package com.zem.diveschool.controllers;

import com.zem.diveschool.data.CourseDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

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

    @RequestMapping("/courses/find")
    public String findCourses(Model model) {
        model.addAttribute("courses", courseDtoService.findAll());
        return "courses/find";
    }

    /* --- */

    @GetMapping("/courses/{courseId}/slots")
    public String listCourseSlots(@PathVariable String courseId, Model model){
        log.debug("Getting slots list for course id: " + courseId);

        Optional<CourseDto> courseDtoOptional = courseDtoService.findById(Long.valueOf(courseId));
        Set<SlotDto> slotsDto = courseDtoService.findSlotsByCourseId(Long.valueOf(courseId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("course", courseDtoOptional.orElse(null));
        return "courses/slots/list";
    }

    @GetMapping("/courses/{courseId}/slots/new")
    public String newCourseSlot(@PathVariable String courseId, Model model) {
        log.debug("Getting course id " + courseId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<CourseDto> courseDtoOptional = courseDtoService.findById(Long.valueOf(courseId));

        // need to return back parent id for hidden form property
        SlotDto slotDto = new SlotDto();
        slotDto.setCourse(courseDtoOptional.get());

        model.addAttribute("slot", slotDto);
        return "students/locations/locationform";
    }

    @GetMapping("/courses/{courseId}/slots/{slotId}/show")
    public String showCourseSlot(@PathVariable String courseId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for course id: " + courseId);

        Optional<CourseDto> courseDtoOptional = courseDtoService.findById(Long.valueOf(courseId));
        Optional<SlotDto> slotDtoOptional =
                courseDtoService.findByCourseIdAndSlotId(Long.valueOf(courseId), Long.valueOf(slotId));

        model.addAttribute("slot", slotDtoOptional.orElse(null));
        model.addAttribute("course", courseDtoOptional.orElse(null));
        return "courses/slots/show";
    }
}
