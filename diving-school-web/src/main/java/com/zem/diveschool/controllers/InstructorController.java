package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.InstructorConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.InstructorExtendedService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Location;
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
public class InstructorController {

    private static final String VIEWS_INSTRUCTORS_INDEX = "instructors/index";
    private static final String VIEWS_INSTRUCTORS_SHOW = "instructors/show";
    private static final String VIEWS_INSTRUCTORS_INSTRUCTORFORM = "instructors/instructorform";
    private static final String VIEWS_INSTRUCTORS_FIND = "instructors/find";

    private static final String VIEWS_INSTRUCTORS_LOCATIONS_LIST = "instructors/locations/list";
    private static final String VIEWS_INSTRUCTORS_LOCATIONS_NEW = "instructors/locations/new";
    private static final String VIEWS_INSTRUCTORS_LOCATIONS_SHOW = "instructors/locations/show";

    private static final String VIEWS_INSTRUCTORS_SLOTS_LIST = "instructors/slots/list";
    private static final String VIEWS_INSTRUCTORS_SLOTS_NEW = "instructors/slots/new";
    private static final String VIEWS_INSTRUCTORS_SLOTS_SHOW =  "instructors/slots/show";

    private static final String REDIRECT_INSTRUCTORS = "redirect:/instructors";
    private static final String REDIRECT_INSTRUCTORS_LOCATIONS = "redirect:/instructors/locations";
    private static final String REDIRECT_INSTRUCTORS_SLOTS = "redirect:/instructors/slots";

    private final InstructorExtendedService service;
    private final InstructorConverter converter;
    private final LocationConverter locationConverter;
    private final SlotConverter slotConverter;

    public InstructorController(InstructorExtendedService service,
                                InstructorConverter converter,
                                LocationConverter locationConverter,
                                SlotConverter slotConverter) {
        this.service = service;
        this.converter = converter;
        this.locationConverter = locationConverter;
        this.slotConverter = slotConverter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/instructors", "/instructors/index", "/instructors/index.html", "instructors.html"})
    public String listInstructors(Model model) {
        Set<Instructor> instructors = service.findAll();
        Set<InstructorDto> instructorsDto = converter.convertFromEntities(instructors);
        model.addAttribute("instructors", instructorsDto);
        return VIEWS_INSTRUCTORS_INDEX;
    }

    @GetMapping({"/instructors/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(id));
        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_SHOW;
    }

    @GetMapping("instructors/new")
    public String newInstructor(Model model) {
        model.addAttribute("instructor", InstructorDto.builder().build());
        return VIEWS_INSTRUCTORS_INSTRUCTORFORM;
    }

    @GetMapping("instructors/{id}/update")
    public String updateInstructor(@PathVariable String id, Model model) {
        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(id));
        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());
        model.addAttribute("instructor", instructorDto);
        return  VIEWS_INSTRUCTORS_INSTRUCTORFORM;
    }

    @PostMapping("instructors")
    public String saveOrUpdate(@ModelAttribute InstructorDto instructorDto) {
        Instructor instructor = converter.convertFromDto(instructorDto);

        Instructor savedInstructor = service.save(instructor);

        InstructorDto savedInstructorDto = converter.convertFromEntity(savedInstructor);
        return REDIRECT_INSTRUCTORS + "/" + savedInstructorDto.getId() + "/show";
    }

    @GetMapping("instructors/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return REDIRECT_INSTRUCTORS;
    }

    /* --- */

    @RequestMapping("/instructors/find")
    public String findInstructors(Model model) {
        Set<Instructor> instructors = service.findAll();
        Set<InstructorDto> instructorsDto = converter.convertFromEntities(instructors);

        model.addAttribute("instructors", instructorsDto);
        return VIEWS_INSTRUCTORS_FIND;
    }

    /* --- */

    @GetMapping("/instructors/{instructorId}/locations")
    public String listInstructorLocations(@PathVariable String instructorId, Model model){
        log.debug("Getting locations list for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        Set<Location> locations = service.findLocationsByInstructorId(Long.valueOf(instructorId));

        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());
        Set<LocationDto> locationsDto = locationConverter.convertFromEntities(locations);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationsDto);
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_LOCATIONS_LIST;
    }

    @GetMapping("/instructors/{instructorId}/locations/new")
    public String newInstructorLocation(@PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());

        LocationDto locationDto = new LocationDto();
        instructorDto.setHomeAddress(locationDto);

        model.addAttribute("location", locationDto);
        return VIEWS_INSTRUCTORS_LOCATIONS_NEW;
    }

    @GetMapping("/instructors/{instructorId}/locations/{locationId}/delete")
    public String deleteInstructorLocation(@PathVariable String instructorId, @PathVariable String locationId,
                                           Model model){
        log.debug("Getting location id " + locationId + " for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        service.deleteByInstructorIdAndLocationId(Long.valueOf(instructorId), Long.valueOf(locationId));

        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());

        model.addAttribute("instructor", instructorDto);
        return REDIRECT_INSTRUCTORS_LOCATIONS;
    }

    @GetMapping("/instructors/{instructorId}/locations/{locationId}/show")
    public String showInstructorLocation(@PathVariable String instructorId, @PathVariable String locationId,
                                         Model model){
        log.debug("Getting location id " + locationId + " for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        Optional<Location> locationOptional =
                service.findByInstructorIdAndLocationId(Long.valueOf(instructorId), Long.valueOf(locationId));

        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());
        LocationDto locationDto = locationConverter.convertFromEntity(locationOptional.get());

        model.addAttribute("location", locationDto);
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_LOCATIONS_SHOW;
    }

    @GetMapping("/instructors/{instructorId}/slots")
    public String listInstructorSlots(@PathVariable String instructorId, Model model){
        log.debug("Getting slots list for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        Set<Slot> slots = service.findSlotsByInstructorId(Long.valueOf(instructorId));

        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());
        Set<SlotDto> slotsDto = slotConverter.convertFromEntities(slots);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slots", slotsDto);
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_SLOTS_LIST;
    }

    @GetMapping("/instructors/{instructorId}/slots/new")
    public String newInstructorSlot(@PathVariable String instructorId, Model model){
        log.debug("Getting instructor id " + instructorId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());

        SlotDto slotDto = new SlotDto();
        instructorDto.getSlots().add(slotDto);

        model.addAttribute("slot", slotDto);
        return VIEWS_INSTRUCTORS_SLOTS_NEW;
    }

    @GetMapping("/instructors/{instructorId}/slots/{slotId}/delete")
    public String deleteInstructorSlot(@PathVariable String instructorId, @PathVariable String slotId,
                                           Model model){
        log.debug("Getting slot id " + slotId + " for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        service.deleteByInstructorIdAndSlotId(Long.valueOf(instructorId), Long.valueOf(slotId));

        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());

        model.addAttribute("instructor", instructorDto);
        return REDIRECT_INSTRUCTORS_SLOTS;
    }

    @GetMapping("/instructors/{instructorId}/slots/{slotId}/show")
    public String showInstructorSlot(@PathVariable String instructorId, @PathVariable String slotId, Model model){
        log.debug("Getting slot id " + slotId + " for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = service.findById(Long.valueOf(instructorId));
        Optional<Slot> slotOptional =
                service.findByInstructorIdAndSlotId(Long.valueOf(instructorId), Long.valueOf(slotId));

        InstructorDto instructorDto = converter.convertFromEntity(instructorOptional.get());
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        model.addAttribute("slot", slotDto);
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_SLOTS_SHOW;
    }
}
