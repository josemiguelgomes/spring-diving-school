package com.zem.diveschool.controllers;

import com.zem.diveschool.converters.impl.simple.InstructorConverter;
import com.zem.diveschool.converters.impl.simple.LocationConverter;
import com.zem.diveschool.converters.impl.simple.SlotConverter;
import com.zem.diveschool.data.InstructorExtendedService;
import com.zem.diveschool.data.SlotExtendedService;
import com.zem.diveschool.dto.InstructorDto;
import com.zem.diveschool.dto.SlotDto;
import com.zem.diveschool.dto.StudentDto;
import com.zem.diveschool.persistence.model.Instructor;
import com.zem.diveschool.persistence.model.Slot;
import com.zem.diveschool.persistence.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class InstructorController {

    private static final String VIEWS_INSTRUCTORS_INDEX = "instructors/index";
    private static final String VIEWS_INSTRUCTORS_SHOW = "instructors/show";
    private static final String VIEWS_INSTRUCTORS_INSTRUCTORFORM = "instructors/instructorform";
    private static final String VIEWS_INSTRUCTORS_IMAGEUPLOADFORM = "instructors/imageuploadform";
    private static final String VIEWS_INSTRUCTORS_FIND = "instructors/find";

    private static final String VIEWS_SLOTS_INSTRUCTORS_LIST = "slots/instructors/list";
    private static final String VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM = "slots/instructor/studentform";
    private static final String VIEWS_SLOTS_INSTRUCTORS_SHOW = "slots/instructors/show";

    private static final String REDIRECT_INSTRUCTORS = "redirect:/instructors";
    private static final String REDIRECT_SLOTS_INSTRUCTORS = "redirect:/slots/instructors";

    private final InstructorExtendedService instructorService;
    private final SlotExtendedService slotService;

    private final InstructorConverter instructorConverter;
    private final LocationConverter locationConverter;
    private final SlotConverter slotConverter;

    public InstructorController(InstructorExtendedService instructorService,
                                SlotExtendedService slotService,
                                InstructorConverter instructorConverter,
                                LocationConverter locationConverter,
                                SlotConverter slotConverter) {
        this.instructorService = instructorService;
        this.slotService = slotService;
        this.instructorConverter = instructorConverter;
        this.locationConverter = locationConverter;
        this.slotConverter = slotConverter;
    }

//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    @GetMapping({"/instructors", "/instructors/index", "/instructors/index.html", "instructors.html"})
    public String listInstructors(Model model) {
        Set<Instructor> instructors = instructorService.findAll();
        Set<InstructorDto> instructorsDto = instructorConverter.convertFromEntities(instructors);
        model.addAttribute("instructors", instructorsDto);
        return VIEWS_INSTRUCTORS_INDEX;
    }

    @GetMapping({"/instructors/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        Optional<Instructor> instructorOptional = instructorService.findById(Long.valueOf(id));
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_SHOW;
    }

    @GetMapping("instructors/new")
    public String newInstructor(Model model) {
        model.addAttribute("instructor", InstructorDto.builder().build());
        return VIEWS_INSTRUCTORS_INSTRUCTORFORM;
    }

    @GetMapping("instructors/{id}/update")
    public String updateInstructor(@PathVariable Long id, Model model) {
        Optional<Instructor> instructorOptional = instructorService.findById(id);
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        model.addAttribute("instructor", instructorDto);
        return  VIEWS_INSTRUCTORS_INSTRUCTORFORM;
    }

    @PostMapping("instructors")
    public String saveOrUpdate(@ModelAttribute InstructorDto instructorDto) {
        Instructor instructor = instructorConverter.convertFromDto(instructorDto);

        Instructor savedInstructor = instructorService.save(instructor);

        InstructorDto savedInstructorDto = instructorConverter.convertFromEntity(savedInstructor);
        return REDIRECT_INSTRUCTORS + "/" + savedInstructorDto.getId() + "/show";
    }

    @GetMapping("instructors/{id}/delete")
    public String deleteById(@PathVariable String id){
        instructorService.deleteById(Long.valueOf(id));
        return REDIRECT_INSTRUCTORS;
    }

    /* --- */

    @GetMapping("/instructors/{instructorId}/photo")
    public String showUploadForm(@PathVariable Long instructorId, @NotNull Model model) {
        Optional<Instructor> instructorOptional = instructorService.findById(instructorId);
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        model.addAttribute("instructor", instructorDto);
        return VIEWS_INSTRUCTORS_IMAGEUPLOADFORM;
    }

    @PostMapping("/instructors/{instructorId}/photo")
    public String handleImagePost(@PathVariable Long instructorId,
                                  @RequestParam("imagefile") MultipartFile file) {
        instructorService.saveImageFile(instructorId, file);
        return REDIRECT_INSTRUCTORS + "/" + instructorId + "/show";
    }

    /* --- */

    @RequestMapping("/instructors/find")
    public String findInstructors(Model model) {
        Set<Instructor> instructors = instructorService.findAll();
        Set<InstructorDto> instructorsDto = instructorConverter.convertFromEntities(instructors);

        model.addAttribute("instructors", instructorsDto);
        return VIEWS_INSTRUCTORS_FIND;
    }

    /* --- */

    @GetMapping("/slots/{slotId}/instructors")
    public String listSlotInstructors(@PathVariable Long slotId, Model model){
        log.debug("Getting instructors list for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(slotId);
        Set<Instructor> instructors = slotService.findInstructorsBySlotId(slotId);

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        Set<InstructorDto> instructorsDto = instructorConverter.convertFromEntities(instructors);

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructors", instructorsDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_INSTRUCTORS_LIST;
    }

    @GetMapping("/slots/{slotId}/instructors/{instructorId}/new")
    public String initNewSlotInstructor(@PathVariable Long slotId, @PathVariable Long instructorId, Model model) {
        log.debug("Getting slot id " + slotId);

        // Find Slot and convert into DTO
        Optional<Slot> slotOptional = slotService.findById(Long.valueOf(slotId));
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        slotDto.setInstructors(instructorConverter.convertFromEntities(slotOptional.get().getInstructors()));

        // Find Student and convert into DTO
        Optional<Instructor> instructorOptional = instructorService.findById(instructorId);
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        instructorDto.setSlots(slotConverter.convertFromEntities(instructorOptional.get().getSlots()));

        // Link the two DTOs
        slotDto.getInstructors().add(instructorDto);
        instructorDto.getSlots().add(slotDto);

        model.addAttribute("slot", slotDto);
        model.addAttribute("instructor", instructorDto);
        return VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM;
    }

    @PostMapping("/slots/{slotId}/instructors/{instructorID}/new")
    public String processNewSlotInstructor(@PathVariable Long slotId,
                                           @PathVariable Long studentId,
                                           BindingResult result, ModelMap model) {

        // Find Slot and convert into DTO
        Optional<Slot> slotOptional = slotService.findById(slotId);
        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        slotDto.setInstructors(instructorConverter.convertFromEntities(slotOptional.get().getInstructors()));

        // Find Student and convert into DTO
        Optional<Instructor> instructorOptional = instructorService.findById(studentId);
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());
        instructorDto.setSlots(slotConverter.convertFromEntities(instructorOptional.get().getSlots()));

        if (!instructorDto.getSlots().isEmpty()
                && instructorDto.getSlots().stream().anyMatch(slot -> slot.getInstructors().contains(instructorDto))) {
            result.rejectValue("instructor", "duplicate", "already exists");
        }

        // Link the two DTOs
        slotDto.getInstructors().add(instructorDto);
        instructorDto.getSlots().add(slotDto);

        if (result.hasErrors()) {
            model.put("slot", slotDto);
            model.put("instructor", instructorDto);
            return VIEWS_SLOTS_INSTRUCTORS_INSTRUCTORFORM;
        } else {
            Slot slot = slotConverter.convertFromDto(slotDto);
            slot.setInstructors(instructorConverter.convertFromDtos(slotDto.getInstructors()));

            Instructor instructor = instructorConverter.convertFromDto(instructorDto);
            instructor.setSlots(slotConverter.convertFromDtos(instructorDto.getSlots()));

            instructorService.save(instructor);

            return REDIRECT_SLOTS_INSTRUCTORS + "/" + slotDto.getId() + "/show";
        }
    }

    @GetMapping("/slots/{slotId}/instructors/{instructorId}/delete")
    public String deleteSlotInstructor(@PathVariable Long slotId, @PathVariable Long instructorId, Model model){
        log.debug("Getting slot id: " + slotId + " and instructor Id: " + instructorId);

        Optional<Slot> slotOptional = slotService.findById(slotId);
        slotService.deleteBySlotIdAndInstructorId(slotId, instructorId);

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());

        // use dto to avoid lazy load errors in Thymeleaf.
        model.addAttribute("slot", slotDto);
        return REDIRECT_SLOTS_INSTRUCTORS;
    }

    @GetMapping("/slots/{slotId}/instructors/{instructorId}/show")
    public String showSlotInstructor(@PathVariable Long slotId, @PathVariable Long instructorId, Model model){
        log.debug("Getting instructor id " + instructorId + " for slot id: " + slotId);

        Optional<Slot> slotOptional = slotService.findById(slotId);
        Optional<Instructor> instructorOptional =
                slotService.findBySlotIdAndInstructorId(slotId, instructorId);

        SlotDto slotDto = slotConverter.convertFromEntity(slotOptional.get());
        InstructorDto instructorDto = instructorConverter.convertFromEntity(instructorOptional.get());

        model.addAttribute("instructor", instructorDto);
        model.addAttribute("slot", slotDto);
        return VIEWS_SLOTS_INSTRUCTORS_SHOW;
    }

}
