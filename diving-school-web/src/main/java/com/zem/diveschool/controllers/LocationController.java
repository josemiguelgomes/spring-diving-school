package com.zem.diveschool.controllers;

import com.zem.diveschool.data.LocationDtoService;
import com.zem.diveschool.dto.LocationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class LocationController {

    private final LocationDtoService locationDtoService;

    public LocationController(LocationDtoService locationDtoService) {
        this.locationDtoService = locationDtoService;
    }

    @RequestMapping({"/locations", "/locations/index", "/locations/index.html", "locations.html"})
    public String listLocations(Model model){
        model.addAttribute("locations", locationDtoService.findAll());
        return "locations/index";
    }

    @RequestMapping({"/locations/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("location",  locationDtoService.findById(Long.valueOf(id)));
        return "locations/show";
    }

    @GetMapping("locations/new")
    public String newLocation(Model model){
        model.addAttribute("location", new LocationDto());
        return "locations/locationform";
    }

    @GetMapping("locations/{id}/update")
    public String updateLocation(@PathVariable String id, Model model){
        model.addAttribute("location", locationDtoService.findById(Long.valueOf(id)));
        return  "locations/locationform";
    }

    @PostMapping("locations")
    public String saveOrUpdate(@ModelAttribute LocationDto locationDto){
        LocationDto savedLocationDto = locationDtoService.save(locationDto);
        return "redirect:/locations/" + savedLocationDto.getId() + "/show";
    }

    @GetMapping("locations/{id}/delete")
    public String deleteById(@PathVariable String id){
        locationDtoService.deleteById(Long.valueOf(id));
        return "redirect:/locations";
    }

    /* ---- */

    @GetMapping("/instructors/{instructorId}/locations")
    public String listInstructorLocations(@PathVariable String instructorId, Model model){
        log.debug("Getting locations list for instructor id: " + instructorId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationDtoService.findByInstructorId(Long.valueOf(instructorId)));
        return "instructors/locations/list";
    }

    @GetMapping("/instructors/{instructorId}/locations/{locationId} ")
    public String showInstructorLocation(@PathVariable String instructorId, @PathVariable String locationId,
                                          Model model){
        log.debug("Getting location id " + locationId + " for instructor id: " + instructorId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("location", locationDtoService.findByInstructionAndLocationId(Long.valueOf(instructorId),
                Long.valueOf(locationId)));
        return "instructors/locations/show";
    }


    @GetMapping("/slots/{slotId}/locations")
    public String listLocationsSlot(@PathVariable String slotId, Model model){
        log.debug("Getting locations list for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationDtoService.findBySlotId(Long.valueOf(slotId)));
        return "slots/locations/list";
    }

    @GetMapping("/slots/{slotIdId}/locations/{locationId}/show")
    public String showLocationSlot(@PathVariable String slotId, @PathVariable String locationId, Model model){
        log.debug("Getting location id " + locationId + " for slot id: " + slotId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("location", locationDtoService.findBySlotIdAndLocationID(Long.valueOf(slotId),
                Long.valueOf(locationId)));
        return "slots/locations/show";
    }

    @GetMapping("/students/{studentId}/locations")
    public String listStudentLocations(@PathVariable String studentId, Model model){
        log.debug("Getting location list for student id: " + studentId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationDtoService.findByStudentId(Long.valueOf(studentId)));
        return "students/locations/list";
    }

    @GetMapping("/students/{studentId}/locations/{locationId}/show")
    public String showStudentLocation(@PathVariable String studentId, @PathVariable String locationId, Model model){
        log.debug("Getting location id " + locationId + " for student id: " + studentId);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("location", locationDtoService.findByStudentIdAndLocationId(Long.valueOf(studentId),
                Long.valueOf(locationId)));
        return "students/locations/show";
    }
}
