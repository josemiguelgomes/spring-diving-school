package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.data.LocationExtendedService;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class LocationController {

    private final LocationExtendedService service;
    private final LocationConverter converter;

    public LocationController(LocationExtendedService service,
                              LocationConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/locations", "/locations/index", "/locations/index.html", "locations.html"})
    public String listLocations(Model model){
        Set<Location> locations = service.findAll();
        Set<LocationDto> locationsDto = converter.convertFromEntities(locations);
        model.addAttribute("locations", locationsDto);
        return "locations/index";
    }

    @GetMapping({"/locations/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Optional<Location> locationOptional = service.findById(Long.valueOf(id));
        LocationDto locationDto = converter.convertFromEntity(locationOptional.get());
        model.addAttribute("location", locationDto);
        return "locations/show";
    }

    @GetMapping("locations/new")
    public String newLocation(Model model){
        model.addAttribute("location", LocationDto.builder().build());
        return "locations/locationform";
    }

    @GetMapping("locations/{id}/update")
    public String updateLocation(@PathVariable String id, Model model){
        Optional<Location> locationOptional = service.findById(Long.valueOf(id));
        LocationDto locationDto = converter.convertFromEntity(locationOptional.get());
        model.addAttribute("location", locationDto);
        return  "locations/locationform";
    }

    @PostMapping("locations")
    public String saveOrUpdate(@ModelAttribute LocationDto locationDto){
        Location location = converter.convertFromDto(locationDto);
        Location savedLocation = service.save(location);
        LocationDto savedLocationDto = converter.convertFromEntity(savedLocation);
        return "redirect:/locations/" + savedLocationDto.getId() + "/show";
    }

    @GetMapping("locations/{id}/delete")
    public String deleteById(@PathVariable String id){
        service.deleteById(Long.valueOf(id));
        return "redirect:/locations";
    }

    /* --- */

    /* ---- */
}
