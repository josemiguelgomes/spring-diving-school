package com.zem.diveschool.controllers;

import com.zem.diveschool.data.LocationDtoService;
import com.zem.diveschool.dto.LocationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
