package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.LocationDto;
import com.zem.diveschool.persistence.model.Location;
import com.zem.diveschool.persistence.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class LocationController {

    private final LocationService locationService;
    private final ConvertObjectToObject<Location, LocationDto> convertToDto;
    private final ConvertObjectToObject<LocationDto, Location> convertToEntity;

    public LocationController(LocationService locationService,
                              ConvertObjectToObject<Location, LocationDto> convertToDto,
                              ConvertObjectToObject<LocationDto, Location> convertToEntity) {
        this.locationService = locationService;
        this.convertToDto = convertToDto;
        this.convertToEntity = convertToEntity;
    }

    @RequestMapping({"/locations", "/locations/index", "/locations/index.html", "locations.html"})
    public String listLocations(Model model){
        model.addAttribute("locations", convertToDto.convert(locationService.findAll()));

        return "locations/index";
    }

    @RequestMapping({"/locations/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Location location = locationService.findById(Long.valueOf(id));
        model.addAttribute("location", convertToDto.convert(location));

        return "locations/show";
    }

    @GetMapping("locations/new")
    public String newLocation(Model model){
        model.addAttribute("location", new LocationDto());

        return "locations/locationform";
    }

    @GetMapping("locations/{id}/update")
    public String updateLocation(@PathVariable String id, Model model){
        model.addAttribute("location", convertToDto.convert(locationService.findById(Long.valueOf(id))));
        return  "locations/locationform";
    }

    @PostMapping("locations")
    public String saveOrUpdate(@ModelAttribute LocationDto locationDto){
        Location savedLocation = locationService.save(convertToEntity.convert(locationDto));
        return "redirect:/cards/" + savedLocation.getId() + "/show";
    }

    @GetMapping("locations/{id}/delete")
    public String deleteById(@PathVariable String id){
        locationService.deleteById(Long.valueOf(id));
        return "redirect:/locations";
    }

    @GetMapping("/api/locations")
    public @ResponseBody Set<LocationDto> getLocationJson(){
        return convertToDto.convert(locationService.findAll());
    }

}
