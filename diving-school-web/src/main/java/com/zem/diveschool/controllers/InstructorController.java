package com.zem.diveschool.controllers;

import com.zem.diveschool.data.InstructorDtoService;
import com.zem.diveschool.dto.CourseDto;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class InstructorController {

    private final InstructorDtoService instructorDtoService;

    public InstructorController(InstructorDtoService instructorDtoService) {
        this.instructorDtoService = instructorDtoService;
    }

    @RequestMapping({"/instructors", "/instructors/index", "/instructors/index.html", "instructors.html"})
    public String listInstructors(Model model) {
        model.addAttribute("instructors", instructorDtoService.findAll());
        return "instructors/index";
    }

    @RequestMapping({"/instructors/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(id));
        model.addAttribute("instructor", instructorDtoOptional.get());
        return "instructors/show";
    }

    @GetMapping("instructors/new")
    public String newInstructor(Model model) {
        model.addAttribute("instructor", new InstructorDto());
        return "instructors/instructorform";
    }

    @GetMapping("instructors/{id}/update")
    public String updateInstructor(@PathVariable String id, Model model) {
        model.addAttribute("instructor", instructorDtoService.findById(Long.valueOf(id)));
        return  "instructors/instructorform";
    }

    @PostMapping("instructors")
    public String saveOrUpdate(@ModelAttribute InstructorDto instructorDto) {
        InstructorDto savedInstructorDto = instructorDtoService.save(instructorDto);
        return "redirect:/instructors/" + savedInstructorDto.getId() + "/show";
    }

    @GetMapping("instructors/{id}/delete")
    public String deleteById(@PathVariable String id){
        instructorDtoService.deleteById(Long.valueOf(id));
        return "redirect:/instructors";
    }

    /* --- */

    @RequestMapping("/instructors/find")
    public String findInstructors(Model model) {
        model.addAttribute("instructors", instructorDtoService.findAll());
        return "instructors/find";
    }

    /* --- */

    @GetMapping("/instructors/{instructorId}/locations")
    public String listInstructorLocations(@PathVariable String instructorId, Model model){
        log.debug("Getting locations list for instructor id: " + instructorId);

        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(instructorId));
        Set<LocationDto> locationsDto = instructorDtoService.findLocationsByInstructorId(Long.valueOf(instructorId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationsDto);
        model.addAttribute("instructor", instructorDtoOptional.orElse(null));
        return "instructors/locations/list";
    }

    @GetMapping("/instructors/{instructorId}/locations/new")
    public String newInstructorLocation(@PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(instructorId));

        LocationDto locationDto = new LocationDto();
        instructorDtoOptional.get().setHomeAddress(locationDto);

        model.addAttribute("location", locationDto);
        return "instructors/locations/new";
    }

    @GetMapping("/instructors/{instructorId}/locations/{locationId}/show")
    public String showInstructorLocation(@PathVariable String instructorId, @PathVariable String locationId,
                                         Model model){
        log.debug("Getting location id " + locationId + " for instructor id: " + instructorId);

        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(instructorId));
        Optional<LocationDto> locationDtoOptional =
             instructorDtoService.findByInstructorIdAndLocationId(Long.valueOf(instructorId), Long.valueOf(locationId));

        model.addAttribute("card", locationDtoOptional.get());
        model.addAttribute("instructor", instructorDtoOptional.get());
        return "instructors/locations/show";
    }

    @GetMapping("/instructors/{instructorId}/slots")
    public String listInstructorSlots(@PathVariable String instructorId, Model model){
        log.debug("Getting slots list for instructor id: " + instructorId);

        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(instructorId));
        Set<SlotDto> slotsDto = instructorDtoService.findSlotsByInstructorId(Long.valueOf(instructorId));

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("instructor", instructorDtoOptional.get());
        return "instructors/slots/list";
    }

    @GetMapping("/instructors/{instructorId}/slots/new")
    public String newInstructorSlot(@PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(instructorId));

        SlotDto slotDto = new SlotDto();
        instructorDtoOptional.get().getSlots().add(slotDto);

        model.addAttribute("slot", slotDto);
        return "instructors/slots/new";
    }

    @GetMapping("/instructors/{instructorId}/slots/{slotId}/show")
    public String showInstructorSlot(@PathVariable String instructorId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for instructor id: " + instructorId);

        Optional<InstructorDto> instructorDtoOptional = instructorDtoService.findById(Long.valueOf(instructorId));
        Optional<SlotDto> slotDtoOptional =
                instructorDtoService.findByInstructorIdAndSlotId(Long.valueOf(instructorId), Long.valueOf(slotId));
        model.addAttribute("slot", slotDtoOptional.orElse(null));
        model.addAttribute("instructor", instructorDtoOptional.orElse(null));
        return "instructors/slots/show";
    }

}
