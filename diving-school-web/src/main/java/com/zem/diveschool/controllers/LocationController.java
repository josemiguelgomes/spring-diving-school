package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.InstructorConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.data.InstructorExtendedService;
import com.zem.diveschool.data.LocationExtendedService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class LocationController {

//    private static final String VIEWS_LOCATIONS_INDEX = "locations/index";
//    private static final String VIEWS_LOCATIONS_SHOW = "locations/show";
//    private static final String VIEWS_LOCATIONS_LOCATIONFORM = "locations/locationform";
      private static final String VIEWS_INSTRUCTORS_LOCATIONS_LIST = "instructors/locations/list";
      private static final String VIEWS_INSTRUCTORS_LOCATIONS_NEW = "instructors/locations/new";
      private static final String VIEWS_INSTRUCTORS_LOCATIONS_LOCATIONFORM = "instructors/locations/locationform";

//    private static final String REDIRECT_LOCATIONS = "redirect:/locations";
      private static final String REDIRECT_INSTRUCTORS_LOCATIONS = "redirect:/instructors/locations";

    private final LocationExtendedService locationService;
    private final InstructorExtendedService instructorService;

    private final LocationConverter locationConverter;
    private final InstructorConverter instructorConverter;


    public LocationController(LocationExtendedService locationService,
                              InstructorExtendedService instructorService,
                              LocationConverter locationConverter,
                              InstructorConverter instructorConverter) {
        this.locationService = locationService;
        this.instructorService = instructorService;
        this.locationConverter = locationConverter;
        this.instructorConverter = instructorConverter;
    }

//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }


    /* --- */

    @GetMapping("/instructors/{instructorId}/locations")
    public String listInstructorLocations(@PathVariable Long instructorId, Model model){
        log.debug("Getting locations list for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = instructorService.findById(instructorId);
        Set<Location> locations = instructorService.findLocationsByInstructorId(instructorId);

        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        Set<LocationDto> locationsDto = locationConverter.convertFromEntities(locations);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("locations", locationsDto);
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_LOCATIONS_LIST;
    }

    @GetMapping("/instructors/{instructorId}/locations/new")
    public String initNewInstructorLocation(@PathVariable Long instructorId, Model model){
        log.debug("Getting instructor id " + instructorId);

        // TODO make sure we have a good id value, raise exception if null
        Optional<Instructor> instructorOptional = instructorService.findById(instructorId);
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());

        LocationDto locationDto = new LocationDto();
        instructorDto.setHomeAddress(locationDto);

        model.addAttribute("location", locationDto);
        return VIEWS_INSTRUCTORS_LOCATIONS_NEW;
    }

    @PostMapping("/instructors/{instructorId}/locations/new")
    public String processNewInstructorLocation(@PathVariable Long instructorId,
                                               LocationDto locationDto,
                                               BindingResult result, ModelMap model) {

        // Find Slot and convert into DTO
        Optional<Instructor> instructorOptional = instructorService.findById(instructorId);
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        instructorDto.setHomeAddress(locationConverter.convertFromEntity(instructorOptional.get().getHomeAddress()));

        // Link the two DTOs
        instructorDto.setHomeAddress(locationDto);

        if (result.hasErrors()) {
            model.put("instructor", instructorDto);
            model.put("location", locationDto);
            return VIEWS_INSTRUCTORS_LOCATIONS_LOCATIONFORM;
        } else {
            Location location = locationConverter.convertFromDto(locationDto);

            Instructor instructor = instructorConverter.convertFromDto(instructorDto);
            instructor.setHomeAddress(location);

            Location locationSaved = locationService.save(location);

            return REDIRECT_INSTRUCTORS_LOCATIONS + "/" + locationSaved.getId() + "/show";
        }
    }

    @GetMapping("/instructors/{instructorId}/locations/{locationId}/delete")
    public String deleteInstructorLocation(@PathVariable Long instructorId,
                                           @PathVariable Long locationId,
                                           Model model){
        log.debug("Getting location id " + locationId + " for instructor id: " + instructorId);

        Optional<Instructor> instructorOptional = instructorService.findById(instructorId);
        instructorService.deleteByInstructorIdAndLocationId(instructorId, locationId);

        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());

        model.addAttribute("instructor", instructorDto);
        return REDIRECT_INSTRUCTORS_LOCATIONS;
    }




    /* ---- */
}
